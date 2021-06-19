package it.skotlinyard.scan4students.utils

// to access the session's instance just Session.instance
object Session {
    private var username: String = String()
    private var logged: Boolean = false

    //add other things that can be needed

    fun isLogged(): Boolean{ return logged}
    fun setLogged(arg: Boolean){logged = arg}
    fun getCurrUsername(): String {return username}
    fun setCurrUsername(name: String){username = name}

}