package com.android.recyclerx.sample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.recyclerx.adapter.RecyclerXAdapter;
import com.android.recyclerx.adapter.aggregator.BaseSectionAggregator;
import com.android.recyclerx.adapter.delegate.BaseSectionDelegate;
import com.android.recyclerx.sample.adapter.aggregator.SectionDelegateAggregator;
import com.android.recyclerx.sample.adapter.delegate.GamesSectionDelegate;
import com.android.recyclerx.sample.adapter.delegate.MoviesSectionDelegate;
import com.android.recyclerx.sample.adapter.delegate.MusicSectionDelegate;
import com.android.recyclerx.sample.entity.GameEntity;
import com.android.recyclerx.sample.entity.MovieEntity;
import com.android.recyclerx.sample.entity.MusicAlbumEntity;
import com.android.recyclerx.sample.entity.MusicArtistEntity;
import com.android.recyclerx.entity.ItemContainer;
import com.android.recyclerx.repository.BaseSectionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity with data for demonstrating how to use the Recycler X library in action.
 *
 * @author Vadim Firsov
 */
public class MainActivity extends AppCompatActivity {

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.activity_main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<BaseSectionDelegate> delegates = new ArrayList<>();
        delegates.add(new MoviesSectionDelegate(new BaseSectionRepository<MovieEntity>()));
        delegates.add(new GamesSectionDelegate(new BaseSectionRepository<GameEntity>()));
        delegates.add(new MusicSectionDelegate(new BaseSectionRepository<ItemContainer>()));

        BaseSectionAggregator aggregator = new SectionDelegateAggregator(delegates);

        List<MovieEntity> movies = new ArrayList<>();
        movies.add(new MovieEntity(ContextCompat.getDrawable(this, R.drawable.avengers), "Avengers: Infinity War"));
        movies.add(new MovieEntity(ContextCompat.getDrawable(this, R.drawable.interstellar), "Interstellar"));
        movies.add(new MovieEntity(ContextCompat.getDrawable(this, R.drawable.deadpool), "Deadpool"));

        List<GameEntity> games = new ArrayList<>();
        games.add(new GameEntity(ContextCompat.getDrawable(this, R.drawable.cod), "Call of Duty: World War II"));
        games.add(new GameEntity(ContextCompat.getDrawable(this, R.drawable.half_life_3), "Half-Life 3"));
        games.add(new GameEntity(ContextCompat.getDrawable(this, R.drawable.halo_5), "Halo 5: Guardians"));

        List<ItemContainer> music = new ArrayList<>();
        music.add(new ItemContainer(new MusicArtistEntity("Armin van Buuren", "Trance")));
        music.add(new ItemContainer(new MusicAlbumEntity(ContextCompat.getDrawable(this, R.drawable.mirage), "Mirage")));
        music.add(new ItemContainer(new MusicAlbumEntity(ContextCompat.getDrawable(this, R.drawable.intense), "Intense")));
        music.add(new ItemContainer(new MusicAlbumEntity(ContextCompat.getDrawable(this, R.drawable.embrace), "Embrace")));

        music.add(new ItemContainer(new MusicArtistEntity("Lindsey Stirling", "Pop Music")));
        music.add(new ItemContainer(new MusicAlbumEntity(ContextCompat.getDrawable(this, R.drawable.shatter_me), "Shatter Me")));
        music.add(new ItemContainer(new MusicAlbumEntity(ContextCompat.getDrawable(this, R.drawable.live_from_london), "Live From London")));
        music.add(new ItemContainer(new MusicAlbumEntity(ContextCompat.getDrawable(this, R.drawable.brave_enough), "Brave Enough")));

        music.add(new ItemContainer(new MusicArtistEntity("Yellowcard", "Pop Punk Rock")));
        music.add(new ItemContainer(new MusicAlbumEntity(ContextCompat.getDrawable(this, R.drawable.ocean_avenue), "Ocean Avenue")));
        music.add(new ItemContainer(new MusicAlbumEntity(ContextCompat.getDrawable(this, R.drawable.paper_walls), "Paper Walls")));
        music.add(new ItemContainer(new MusicAlbumEntity(ContextCompat.getDrawable(this, R.drawable.lights_and_sounds), "Lights and Sounds")));

        RecyclerXAdapter recyclerXAdapter = new RecyclerXAdapter(aggregator);
        recyclerView.setAdapter(recyclerXAdapter);
        recyclerXAdapter.addAllItems(MovieEntity.class, movies);
        recyclerXAdapter.addAllItems(GameEntity.class, games);
        recyclerXAdapter.addAllItems(MusicArtistEntity.class, music);
    }
}