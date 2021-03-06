package com.example.peliculas2.bd.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.peliculas2.bd.dao.ClasificacionDao
import com.example.peliculas2.bd.dao.MainBaseDatos
import com.example.peliculas2.bd.entidades.ClasificacionEntity
import com.example.peliculas2.bd.repository.ClasificacionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ClasificacionViewModel(application: Application): AndroidViewModel(application) {
    val lista : LiveData<List<ClasificacionEntity>>
    private val repository: ClasificacionRepository
    init {
        val clasificacionDao =
            MainBaseDatos.getDataBase(application).clasificacionDao()
        repository = ClasificacionRepository(clasificacionDao)
        lista = repository.listado
    }
    fun agregarClasificacion(clasificacion: ClasificacionEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addClasificacion(clasificacion)
        }
    }
    fun actualizarClasificacion(clasificacion: ClasificacionEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateClasificacion(clasificacion)
        }
    }
    fun eliminarClasificacion(clasificacion: ClasificacionEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteClasificacion(clasificacion)
        }
    }
    fun eliminarTodo(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}