package com.oritmalki.mymusicapp2.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import com.oritmalki.mymusicapp2.model.Measure;

import java.util.List;

/**
 * Created by Orit on 7.1.2018.
 */

@Dao
public abstract class MeasureDao {
    @Transaction @Query("SELECT * FROM measure")
    abstract LiveData<List<Measure>> getAll();

    @Transaction @Query("SELECT * FROM measure where measure_number LIKE :measureNumber")
    abstract LiveData<Measure> getMeasure(int measureNumber);

//    @Query("SELECT beats FROM measure WHERE measure_number LIKE :measureNumber")
//    LiveData<List<Beat>> getBeats(int measureNumber);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract void insertAll(List<Measure> measures);

    @Insert
    abstract void newMeasure(Measure measure);

    @Delete
    abstract void delete(Measure measure);

    @Delete
    abstract void deleteAll(List<Measure> measures);

    @Transaction
    public void insertAndDeleteInTransaction(Measure newMeasure, Measure oldMeasure, List<Measure> measures) {
        newMeasure(newMeasure);
        delete(oldMeasure);
        insertAll(measures);
    }

}
