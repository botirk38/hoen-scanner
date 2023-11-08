package com.skyscanner;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {

    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) {

        ObjectMapper mapper = new ObjectMapper();

        List<SearchResult> carResults = Arrays.asList(
                mapper.readValue(
                        getClass().getResource("rental_cars.json"),
                        SearchResult[].class
                )
        );

        List<SearchResult> hotelResults = Array.asList(
                mapper.readValue(
                        getClass().getResource("hotels.json"),
                        SearchResult[].class
                )
        );

        List<SearchResult> searchResults = new ArrayList<>();
        searchResults.addAll(carResults);
        searchResults.addAll(hotelResults);
        final SearchResource searchResource = new SearchResource(searchResults);
        environment.jersey().register(searchResource);

    }

}
