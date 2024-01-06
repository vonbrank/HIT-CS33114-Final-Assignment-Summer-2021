// @ts-check

const fs = require("fs");
const mysql = require("mysql2");
const dotenv = require("dotenv");
const moment = require("moment-timezone");

dotenv.config({ path: `${__dirname}/config.env` });

const connection = mysql.createConnection({
  host: process.env.DATABASE_LOCAL || "",
  user: process.env.DATABASE_USER_NAME || "",
  password: process.env.DATABASE_USER_PASSWORD || "",
  database: process.env.DATABASE_NAME || "",
});

const tableNames = ["courses", "departments", "users"];

const createCourseTableSQLs = tableNames.map((tableName) =>
  fs.readFileSync(`${__dirname}/sql/create-${tableName}-table.sql`, "utf-8")
);

/** @type {import("./import-dev-data").ICourse[]} */
const coursesData = JSON.parse(
  fs.readFileSync(`${__dirname}/courses.json`, "utf-8")
);
/** @type {import("./import-dev-data").IDepartment[]} */
const departmentsData = JSON.parse(
  fs.readFileSync(`${__dirname}/departments.json`, "utf-8")
);
/** @type {import("./import-dev-data").IUser[]} */
const usersData = JSON.parse(
  fs.readFileSync(`${__dirname}/users.json`, "utf-8")
);

const dropTables = () => {
  tableNames.forEach((tableName) => {
    connection.execute(`DROP TABLE IF EXISTS ${tableName}`);
  });
};

const createTables = () => {
  createCourseTableSQLs.forEach((createCourseTableSQL) =>
    connection.execute(createCourseTableSQL)
  );
};

const insertData = () => {
  const teachersData = usersData.filter(
    (userData) => userData.role === "teacher"
  );

  coursesData.forEach((course) => {
    connection.execute(
      "INSERT INTO courses (id, name, course_credit, credit_hour, department, teacher) VALUES (?, ?, ?, ?, ?, ?)",
      [
        course.id,
        course.name,
        course.course_credit,
        course.credit_hour,
        course.department,
        teachersData[Math.floor(Math.random() * teachersData.length)].id,
      ]
    );
  });
  departmentsData.forEach((department) => {
    connection.execute("INSERT INTO departments (id, name) VALUES (?, ?)", [
      department.id,
      department.name,
    ]);
  });
  usersData.forEach((user) => {
    connection.execute(
      "INSERT INTO users (id, first_name, last_name, email, password, role, department, password_changed_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
      [
        user.id,
        user.first_name,
        user.last_name,
        user.email,
        "",
        user.role,
        user.department,
        moment().utc().format(),
      ]
    );
  });
};

switch (process.argv[2]) {
  case "--import":
    createTables();
    insertData();
    break;
  case "--delete":
    dropTables();
    break;
  default:
}

connection.end();
