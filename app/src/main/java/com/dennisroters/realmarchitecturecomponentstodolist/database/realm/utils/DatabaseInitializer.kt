//package com.dennisroters.realmarchitecturecomponentstodolist.database.realm.utils
//
//import android.util.Log
//import io.realm.Realm
//import java.util.*
//
//object DatabaseInitializer {
//
//    // Simulate a blocking operation delaying each Loan insertion with a delay:
//    private val DELAY_MILLIS = 500
//
//    private val populateWithTestDataTx: Realm.Transaction = Realm.Transaction { db ->
//        db.deleteAll()
//        checkpoint(db)
//
//        val user1 = addUser(db, "1", "Jason", "Seaver", 40)
//        val user2 = addUser(db, "2", "Mike", "Seaver", 12)
//        addUser(db, "3", "Carol", "Seaver", 15)
//
//        val book1 = addBook(db, "1", "Dune")
//        val book2 = addBook(db, "2", "1984")
//        val book3 = addBook(db, "3", "The War of the Worlds")
//        val book4 = addBook(db, "4", "Brave New World")
//        addBook(db, "5", "Foundation")
//        try {
//            // Loans are added with a delay, to have time for the UI to react to changes.
//
//            val today = getTodayPlusDays(0)
//            val yesterday = getTodayPlusDays(-1)
//            val twoDaysAgo = getTodayPlusDays(-2)
//            val lastWeek = getTodayPlusDays(-7)
//            val twoWeeksAgo = getTodayPlusDays(-14)
//
//            addLoan(db, user1, book1, twoWeeksAgo, lastWeek)
//            Thread.sleep(DELAY_MILLIS.toLong())
//            addLoan(db, user2, book1, lastWeek, yesterday)
//            Thread.sleep(DELAY_MILLIS.toLong())
//            addLoan(db, user2, book2, lastWeek, today)
//            Thread.sleep(DELAY_MILLIS.toLong())
//            addLoan(db, user2, book3, lastWeek, twoDaysAgo)
//            Thread.sleep(DELAY_MILLIS.toLong())
//            addLoan(db, user2, book4, lastWeek, today)
//            Log.d("DB", "Added loans")
//
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
//    }
//
//    fun populateAsync(db: Realm) {
//        val task = populateWithTestDataTx
//        db.executeTransactionAsync(task)
//    }
//
//    private fun addLoan(
//        db: Realm,
//        user: User, book: Book, from: Date, to: Date
//    ) {
//        db.loanDao().addLoan(from, to, user.id!!, book.id!!)
//        checkpoint(db)
//    }
//
//    private fun addBook(db: Realm, id: String, title: String): Book {
//        val book = db.bookDao().createOrUpdate(Book(id, title))
//        checkpoint(db)
//        return book!!
//    }
//
//    private fun addUser(
//        db: Realm, id: String, name: String,
//        lastName: String, age: Int
//    ): User {
//        val user = db.userDao().createOrUpdate(User(id, name, lastName, age))
//        checkpoint(db)
//        return user!!
//    }
//
//    private fun getTodayPlusDays(daysAgo: Int): Date {
//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.DATE, daysAgo)
//        return calendar.time
//    }
//
//    /**
//     * Commit the current transaction and start a new one.
//     * This works well for this demo to simulate new updates streaming into
//     * realm.  Not recommended for a real world app.
//     */
//    private fun checkpoint(db: Realm) {
//        db.commitTransaction()
//        db.beginTransaction()
//    }
//
//}