package com.apps.movieapp.persistence;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.apps.movieapp.models.Keyword;
import com.apps.movieapp.models.Review;
import com.apps.movieapp.models.Video;
import com.apps.movieapp.models.entities.Tv;
import com.apps.movieapp.persistence.converters.IntegerListConverter;
import com.apps.movieapp.persistence.converters.KeywordListConverter;
import com.apps.movieapp.persistence.converters.ReviewListConverter;
import com.apps.movieapp.persistence.converters.StringListConverter;
import com.apps.movieapp.persistence.converters.VideoListConverter;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TvDao_Impl implements TvDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Tv> __insertionAdapterOfTv;

  private final KeywordListConverter __keywordListConverter = new KeywordListConverter();

  private final VideoListConverter __videoListConverter = new VideoListConverter();

  private final ReviewListConverter __reviewListConverter = new ReviewListConverter();

  private final StringListConverter __stringListConverter = new StringListConverter();

  private final IntegerListConverter __integerListConverter = new IntegerListConverter();

  private final EntityDeletionOrUpdateAdapter<Tv> __updateAdapterOfTv;

  public TvDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTv = new EntityInsertionAdapter<Tv>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Tv` (`page`,`keywords`,`videos`,`reviews`,`poster_path`,`popularity`,`id`,`backdrop_path`,`vote_average`,`overview`,`first_air_date`,`origin_country`,`genre_ids`,`original_language`,`vote_count`,`name`,`original_name`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Tv value) {
        stmt.bindLong(1, value.getPage());
        final St