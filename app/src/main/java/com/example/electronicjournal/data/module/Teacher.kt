package com.example.electronicjournal.data.module

data class Teacher(
    override val id: Int,
    override val nickname: String,
    override val password: String,
    override val name: String,
    override val lastname: String,
    override val patronymic: String,
    val idFaculty: Int,
): User
