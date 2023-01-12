package com.zainul.buildrrr.maindev.ui.home

class DataClassPost{
    var inptipe: String? = null
    var inplokasi: String? = null
    var inpharga: String? = null
    var inpbunga: String? = null
    var inpdeskripsi: String? = null
    var inpdetail: String? = null
    var image: String? = null

    constructor(
        inptipe: String?, inplokasi: String?, inpharga: String?,
        inpbunga: String?, inpdeskripsi: String?,inpdetail: String?,image: String?
    ){
        this.inptipe = inptipe
        this.inplokasi = inplokasi
        this.inpharga = inpharga
        this.inpbunga = inpbunga
        this.inpdeskripsi = inpdeskripsi
        this.inpdetail = inpdetail
        this.image = image

    }
    constructor()
    {}
}
