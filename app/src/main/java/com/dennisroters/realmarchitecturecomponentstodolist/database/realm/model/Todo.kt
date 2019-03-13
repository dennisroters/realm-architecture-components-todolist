package com.dennisroters.realmarchitecturecomponentstodolist.database.realm.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Todo(
    @PrimaryKey
    var id: String? = null,

    var title: String = ""

) : RealmModel {

}