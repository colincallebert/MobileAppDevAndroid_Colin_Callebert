package com.example.boerderij.fake

import com.example.boerderij.data.AppRepository
import com.example.boerderij.model.condition.ConditionDetail

import com.example.boerderij.model.newsArticle.NewsArticle
import com.example.boerderij.model.newsArticle.NewsArticleDetail

import com.example.boerderij.network.conditionApi.Condition
import com.example.boerderij.network.doctorApi.Doctor
import com.example.boerderij.ui.model.doctors.Doctor.DoctorDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


class FakeApiAppRepository : AppRepository {
    override fun getConditions(): Flow<List<Condition>> {
        return flowOf(FakeDataSource.conditions.toMutableList())
    }

    override suspend fun getConditionDetailById(id: Int): ConditionDetail? {
        return FakeDataSource.conditionDetails.find { it.id == id }
    }

    override fun getDoctors(): Flow<List<Doctor>> {
        return flowOf(FakeDataSource.doctors.toMutableList())
    }

    override suspend fun getDoctorDetailById(id: Int): DoctorDetail? {
        return FakeDataSource.doctorDetails.find { it.id == id }
    }

    override fun getNewsArticles(): Flow<List<NewsArticle>> {
        return flowOf(FakeDataSource.newsArticles.toMutableList())
    }

    override suspend fun getNewsDetailById(id: Int): NewsArticleDetail? {
        return FakeDataSource.newsArticleDetails.find { it.id == id }
    }

    override suspend fun insertCondition(condition: Condition) {
        FakeDataSource.conditions.toMutableList().add(condition)
    }

    override suspend fun insertDoctor(doctor: Doctor) {
        FakeDataSource.doctors.toMutableList().add(doctor)
    }

    override suspend fun insertNewsArticle(newsArticle: NewsArticle) {
        FakeDataSource.newsArticles.toMutableList().add(newsArticle)
    }

    override suspend fun refreshConditions() {
        FakeDataSource.conditions.toMutableList().clear()

    }

    override suspend fun refreshDoctors() {
        FakeDataSource.doctors.toMutableList().clear()
    }

    override suspend fun refreshNewsArticles() {
        FakeDataSource.newsArticles.toMutableList().clear()
    }
}