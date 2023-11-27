# Artists and tracks service

- App runs on port 8080
- To run the app: </br>
```./gradlew bootRun```

To test the service you can use the file bundled with this repo `ArtistDemoService.postman_collection.json`

## Details

Features that were explicitly asked for in the task are built and tested.
- [x] Create a track
- [x] Update an Artist
- [x] Get all tracks of an Artist
- [x] Get artist of the moment (cycles every 3 minutes)

Endpoints implemented to ease the testing:
- [x] Get all artists and their data

Service loads test data from `data.sql` file, it can be found in `resources`.


## Notes

Features that have been on my mind but weren't implemented:
- [ ] Pagination of all artists endpoint
- [ ] Pagination of all tracks of an artist endpoint
- [ ] Storing artist of the day info into the database
- [ ] Implementing transactional details in case more instances of the app would be running
- [ ] Using DB other than in memory H2
- [ ] Creating a docker image
- [ ] Probably adding some more test cases