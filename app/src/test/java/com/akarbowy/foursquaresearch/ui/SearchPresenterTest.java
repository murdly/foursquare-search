package com.akarbowy.foursquaresearch.ui;

import com.akarbowy.foursquaresearch.network.FoursquareService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    @Mock
    SearchContract.View view;

    @Mock
    FoursquareService service;

    @InjectMocks
    SearchPresenter presenter;

    @Before
    public void setUp() throws Exception {
        when(view.isActive()).thenReturn(true);
    }

    @Test public void noteIsSet() {
        String query = "dummy";
//
        presenter.search(query);

        InOrder inOrder = inOrder(view);
        inOrder.verify(view).setLoading(true);

//        when(service.search(query, "dummy"))
//        verify(view.setVenues());


        inOrder.verify(view).setLoading(false);
    }

}