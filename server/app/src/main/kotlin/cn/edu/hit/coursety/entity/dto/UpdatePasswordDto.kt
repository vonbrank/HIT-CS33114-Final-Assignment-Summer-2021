package cn.edu.hit.coursety.entity.dto

data class UpdatePasswordDto(val currentPassword: String, val newPassword: String, val newPasswordConfirm: String)
