export interface ICourse {
  id: number;
  name: string;
  course_credit: number;
  credit_hour: number;
  department: number;
  teacher: number;
}

export interface IDepartment {
  id: number;
  name: string;
}

export interface IUser {
  id: number;
  first_name: string;
  last_name: string;
  email: string;
  role: "admin" | "teacher" | "student";
  department: number;
}
