package com.apps.notesapp.di

import com.apps.notesapp.data.network.GrpcService

object Injector {

    private fun provideGrpcService() = GrpcService(
        host = "192.168.1.107",
        port = 50051
    )

}
