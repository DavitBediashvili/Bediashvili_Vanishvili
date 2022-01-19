package com.example.myapplication.Model

class User {
    private var usernameRead: String = ""
    private var image: String = ""
    private var uid: String = ""


    constructor()


    constructor(usernameRead: String, image: String, uid: String) {
        this.usernameRead = usernameRead;
        this.image = image;
        this.uid = uid


    }

    fun getUsername(): String
    {
        return usernameRead
    }
    fun setUsername(usernameRead: String)
    {
        this.usernameRead = usernameRead
    }

    fun getimage(): String
    {
        return image
    }
    fun setimage(image: String)
    {
        this.image = image
    }

    fun getuid(): String
    {
        return image
    }
    fun setuid(uid: String)
    {
        this.uid = uid
    }
}



