package ktpackage

class ContactData {
    private val objectList: MutableList<ContactModel>
    val all: List<ContactModel>
        get() = objectList

    fun getById(id: Int): ContactModel {
        return objectList[id]
    }

    fun update(rec: ContactModel) {
        objectList[objectList.indexOf(rec)] = rec
    }

    fun delete(rec: ContactModel) {
        objectList.remove(rec)
    }

    fun loadData() {
        objectList.clear()
        objectList.add(ContactModel("James", "Devos", "james.f@gmail.com", "james_d"))
        objectList.add(ContactModel("Sonya", "Topic", "m.combat@yahoo.com", "sonya_t"))
        objectList.add(ContactModel("Robin", "Hood", "sherwood@hero.eu", "robin_h"))
        objectList.add(ContactModel("Stepan", "Jobs", "s.jobs@tntu.ua", "stepan_j"))
    }

    init {
        objectList = arrayListOf()
        loadData()
    }
}