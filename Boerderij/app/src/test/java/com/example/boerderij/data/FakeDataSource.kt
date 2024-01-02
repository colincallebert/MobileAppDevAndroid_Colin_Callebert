package com.example.boerderij.data


import com.example.boerderij.model.registration.Registration
import com.example.boerderij.network.activityApi.Activity

object FakeDataSource {
    val activities = listOf(
        Activity(
            id = 1,
            title = "’t Winkeltje",
            description = "Verkoop van zelfgemaakte en biologische producten in ons winkeltje, gerund door vrijwilligers van de ouderenboerderij. De winst wordt geïnvesteerd in onze werking.|winkel|Welkom in ’t winkeltje. Hier worden zelfgemaakte en bio producten verkocht van in onze ouderenboerderij. De mensen die instaan voor ’t winkeltje zijn vrijwilligers van op de ouderenboerderij. Daarnaast gaat wordt alle winst geïnvesteerd in onze werking. ",
            starttime = "2023-01-08T15:00:00",
            endtime = "2023-01-08T16:30:00",
            maxregistrations = 15,
            amount = 0
        ),
        Activity(
            id = 2,
            title = "Moestuin",
            description = "Zelf groenten en fruit kweken in onze moestuin. De opbrengst wordt gebruikt voor kooklessen en verkoop in het winkeltje. Vrijwilligers helpen bij het onderhoud.|tuin|In onze eigen moestuin kweken wij verschillende soorten groenten en fruit dat kan gebruikt worden om te koken of om te verkopen in ’t winkeltje. Het onderhoud van onze moestuin gebeurt door onze vrijwilligers.",
            starttime = "2023-01-15T15:00:00",
            endtime = "2023-01-15T16:30:00",
            maxregistrations = 15,
            amount = 0
        ),
        Activity(
            id = 3,
            title = "Dierenverzorging",
            description = "Als vrijwilliger kun je meehelpen met de verzorging van onze dieren. Taken omvatten borstelen, wandelen en voeren. Ervaar de vreugde van dierenvriendschap!|dierenverzorging|Als vrijwilliger in onze ouderenboerderij kan je er ook voor kiezen om in te staan voor de verzorging van onze dieren. Hiertoe behoort een uiteenlopend takenpakket. Enkele voorbeelden zijn: borstelen, wandelen, eten geven…",
            starttime = "2023-01-22T15:00:00",
            endtime = "2023-01-22T16:30:00",
            maxregistrations = 15,
            amount = 2
        )
    )
    val registrations = listOf(
        Registration(
            userid = 1,
            activityid = 1,
            amount = 1
        ),
        Registration(
            userid = 1,
            activityid = 3,
            amount = 1
        )
    )
}