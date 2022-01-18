package com.example.myapplication.Model

class User {

    private var username: String = ""
    private var fullName: String = ""
    private var bio: String = ""
    private var image: String = ""
    private var idUser: String = ""

    constructor()

    constructor(username: String, fullName: String, bio: String, image: String, idUser: String) {
        this.username = username
        this.bio = bio
        this.fullName = fullName
        this.image = image
        this.idUser = idUser
    }

     fun getUserName(): String {

        return username


    }
     fun setUserName(username: String){
        this.username = username
    }

     fun getFullName(): String {

        return fullName


    }
     fun setFullName(username: String){
        this.fullName = fullName
    }
     fun getImage(): String {

        return image


    }
    fun setImage(username: String){
        this.image = image
    }
     fun getBio(): String {

        return bio

    }
     fun setBio(username: String){
        this.bio = bio
    }

     fun getId(): String {

        return idUser


    }
     fun setId(username: String){
        this.idUser = idUser
    }


}

