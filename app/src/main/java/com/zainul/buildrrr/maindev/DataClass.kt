package com.zainul.buildrrr.maindev

class DataClass {
    var datanama: String? = null
    var dataemail: String? = null
    var dataImage: String? = null
    var dataImage2: String? = null
    var profile: String? = null

    constructor(
        datanama: String?, dataemail: String?, dataImage: String?,
        dataImage2: String?, profile: String?
    ){
        this.datanama = datanama
        this.dataemail = dataemail
        this.dataImage2 = dataImage2
        this.dataImage = dataImage
        this.profile = profile

    }
    constructor()
    {}
}
