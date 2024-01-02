package com.example.boerderij.fake

import com.example.boerderij.model.condition.ConditionDetail
import com.example.boerderij.model.condition.Symptom
import com.example.boerderij.model.newsArticle.NewsArticle
import com.example.boerderij.model.newsArticle.NewsArticleDetail
import com.example.boerderij.network.conditionApi.Condition
import com.example.boerderij.network.doctorApi.Doctor
import com.example.boerderij.ui.model.doctors.Doctor.DoctorDetail

object FakeDataSource {

    val newsArticles = listOf(
        NewsArticle(
            1,
            "author1",
            "description1",

            ),
        NewsArticle(
            2,
            "author2",
            "description2",

            ),
        NewsArticle(
            2,
            "author3",
            "description3",
        )
    )

    val newsArticleDetails = listOf(
        NewsArticleDetail(
            id = 1,
            title = "Title 1",
            image = "url1",
            firstname = "John",
            lastname = "Doe",
            specialization = "Technology",
            datePosted = "2023-01-01",
            newsText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
        ),
        NewsArticleDetail(
            id = 2,
            title = "Title 2",
            image = "url2",
            firstname = "Jane",
            lastname = "Smith",
            specialization = "Science",
            datePosted = "2023-02-01",
            newsText = "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas."
        ),
        NewsArticleDetail(
            id = 3,
            title = "Title 3",
            image = "url3",
            firstname = null,
            lastname = null,
            specialization = null,
            datePosted = "2023-03-01",
            newsText = "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        )
    )
    val doctors = listOf(
        Doctor(
            1,
            "Doe",
            "Cardiologist",
            "afdasdf",
            "doctor1.jpg"
        ),
        Doctor(
            1,
            "Smith",
            "Pediatrician",
            "sdfa",
            "doctor2.jpg"
        ),
        Doctor(
            1,
            "Johnson",
            "Orthopedic Surgeon",
            "skdjfhs",
            "doctor2.jpg"
        )
    )
    val doctorDetails = listOf(
        DoctorDetail(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            description = "Dr. John Doe is an experienced Cardiologist with a focus on heart health.",
            specialization = "Cardiologist",
            image = "doctor1.jpg"
        ),
        DoctorDetail(
            id = 2,
            firstName = "Jane",
            lastName = "Smith",
            description = "Dr. Jane Smith specializes in Pediatrics and has a passion for children's health.",
            specialization = "Pediatrician",
            image = "doctor2.jpg"
        ),
        DoctorDetail(
            id = 3,
            firstName = "Robert",
            lastName = "Johnson",
            description = "Dr. Robert Johnson is an Orthopedic Surgeon, dedicated to providing top-notch orthopedic care.",
            specialization = "Orthopedic Surgeon",
            image = null
        )
    )
    val conditions = listOf(
        Condition(
            1,
            "High blood pressure",
            "Hypertension is a common condition in which the long-term force of the blood against your artery walls is high enough that it may eventually cause health problems.",

            ),
        Condition(
            2,
            "High blood sugar",
            "Diabetes is a chronic condition that affects how your body turns food into energy. There are three main types of diabetes: type 1, type 2, and gestational diabetes.",

            ),
        Condition(
            3,
            "Severe headache",
            "A migraine is a type of headache characterized by a throbbing or pulsating sensation, usually on one side of the head. It may be accompanied by nausea, vomiting, and sensitivity to light and sound.",

            )
    )

    val symptoms = listOf(
        Symptom(name = "Headache"),
        Symptom(name = "Fatigue"),
        Symptom(name = "Nausea"),
        Symptom(name = "Blurred vision"),
        Symptom(name = "Frequent urination")
    )

    val conditionDetails = listOf(
        ConditionDetail(
            id = 1,
            name = "Hypertension",
            longDescription = "Hypertension is a common condition in which the long-term force of the blood against your artery walls is high enough that it may eventually cause health problems. It is often referred to as high blood pressure."
        ),
        ConditionDetail(
            id = 2,
            name = "Diabetes",
            longDescription = "Diabetes is a chronic condition that affects how your body turns food into energy. There are three main types of diabetes: type 1, type 2, and gestational diabetes. It is characterized by high blood sugar levels."
        ),
        ConditionDetail(
            id = 3,
            name = "Migraine",
            longDescription = "A migraine is a type of headache characterized by a throbbing or pulsating sensation, usually on one side of the head. It may be accompanied by nausea, vomiting, and sensitivity to light and sound."
        )
    )


}