package com.akarbowy.foursquaresearch.ui;

import com.akarbowy.foursquaresearch.data.DataSource;
import com.akarbowy.foursquaresearch.data.model.VenueItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    private static List<VenueItem> VENUES;

    @Mock
    SearchContract.View view;
    @Mock
    DataSource dataSource;
    @InjectMocks
    SearchPresenter presenter;
    @Captor
    ArgumentCaptor<DataSource.GetVenuesCallback> getVenuesCallback;

    @Before
    public void setUp() throws Exception {
        when(view.isActive()).thenReturn(true);

        VENUES = new ArrayList<>();
        VENUES.add(new VenueItem());
        VENUES.add(new VenueItem());
        VENUES.add(new VenueItem());
    }
    @Test
    public void doesNotCallViewMethods_WhenNoView() {
        String query = "dummy";

        presenter.search(query);

        verify(view, never()).setLoading(false);
    }

    @Test
    public void showsList_WhenResults() {
        String query = "dummy";

        presenter.search(query);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).setLoading(true);

        verify(dataSource).loadVenues(eq(query), getVenuesCallback.capture());
        getVenuesCallback.getValue().onDataLoaded(VENUES);

        inOrder.verify(view).setLoading(false);

        ArgumentCaptor<List> setVenuesCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).setVenues(setVenuesCaptor.capture());
        assertTrue(setVenuesCaptor.getValue().size() == VENUES.size());

        verify(view).setEmptyState(false);
    }

    @Test
    public void showsEmptyState_WhenNoResults() {
        String query = "dummy";

        presenter.search(query);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).setLoading(true);

        verify(dataSource).loadVenues(eq(query), getVenuesCallback.capture());
        getVenuesCallback.getValue().onDataLoaded(new ArrayList<VenueItem>());

        inOrder.verify(view).setLoading(false);

        ArgumentCaptor<List> setVenuesCaptor = ArgumentCaptor.forClass(List.class);
        verify(view).setVenues(setVenuesCaptor.capture());
        assertTrue(setVenuesCaptor.getValue().size() == 0);

        verify(view).setEmptyState(true);
    }

    @Test
    public void showsError_WhenNoDataAvailable() {
        String query = "dummy";

        presenter.search(query);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).setLoading(true);

        verify(dataSource).loadVenues(eq(query), getVenuesCallback.capture());
        getVenuesCallback.getValue().onDataNotAvailable();

        inOrder.verify(view).setLoading(false);

        verify(view).showError();
    }
}