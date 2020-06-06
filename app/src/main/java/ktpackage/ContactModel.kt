package ktpackage

class ContactModel {
    var firstName: String? = null
    var lastName: String? = null
    var mail: String? = null
    var photo: String? = null

    constructor() {}
    constructor(
            firstName: String?,
            lastName: String?,
            mail: String?,
            photo: String?) {
        this.firstName = firstName
        this.lastName = lastName
        this.mail = mail
        this.photo = photo
    }

}