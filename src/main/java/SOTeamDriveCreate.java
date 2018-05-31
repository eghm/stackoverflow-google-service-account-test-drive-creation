    import java.io.FileInputStream;
    import java.io.IOException;
    import java.util.Arrays;
    import java.util.List;
    import java.util.UUID;
    
    import com.google.api.client.auth.oauth2.Credential;
    import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
    import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
    import com.google.api.client.http.HttpTransport;
    import com.google.api.client.json.JsonFactory;
    import com.google.api.client.json.jackson2.JacksonFactory;
    import com.google.api.client.util.store.FileDataStoreFactory;
    import com.google.api.services.drive.Drive;
    import com.google.api.services.drive.DriveScopes;
    import com.google.api.services.drive.model.TeamDrive;
    
    
    public class SOTeamDriveCreate {
    
        /** Application name. */
        private static final String APPLICATION_NAME =
                "Drive API Java Quickstart";
    
        
        /** Directory to store user credentials for this application. */
        private static final java.io.File DATA_STORE_DIR = new java.io.File(
                System.getProperty("user.home"), ".credentials/drive-java-quickstart");
    
        /** Global instance of the {@link FileDataStoreFactory}. */
        private static FileDataStoreFactory DATA_STORE_FACTORY;
    
        /** Global instance of the JSON factory. */
        private static final JsonFactory JSON_FACTORY =
                JacksonFactory.getDefaultInstance();
    
        /** Global instance of the HTTP transport. */
        private static HttpTransport HTTP_TRANSPORT;
    
        private static Drive service = null;
    
        protected static String FILE_FIELDS = "id, name, createdTime, mimeType, trashed, permissions, parents, teamDrive";
    
        /**
         * Global instance of the scopes.
         *
         * If modifying these scopes, delete your previously saved credentials
         * at ~/.credentials/drive-java-quickstart
         */
        private static final List<String> SCOPES =
                Arrays.asList(DriveScopes.DRIVE);
    
        private String authFile;
    
    //    private DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
    
        static {
            try {
                HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
                DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            } catch (Throwable t) {
                t.printStackTrace();
                System.exit(1);
            }
        }
    
        public SOTeamDriveCreate(String authFile) {
            this.authFile = authFile;
        }
    
        /**
         * Creates an authorized Credential object.
         *
         * @return an authorized Credential object.
         * @throws IOException
         */
        public Credential authorize() throws IOException {
            GoogleCredential credential = GoogleCredential
                    .fromStream(new FileInputStream(authFile))
                    .createScoped(Arrays.asList(DriveScopes.DRIVE));
            return credential;
        }
    
        /**
         * Build and return an authorized Drive client service.
         *
         * @return an authorized Drive client service
         * @throws IOException
         */
        public Drive getDriveService() throws IOException {
            if (service == null) {
                Credential credential = authorize();
                service = new Drive.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, credential)
                                .setApplicationName(APPLICATION_NAME)
                                .build();
            }
            return service;
        }
    
        public static void main(String[] args) throws Exception {
            SOTeamDriveCreate createTeamDrive = new SOTeamDriveCreate("client_secrets.json");
            createTeamDrive.createTeamDrive();
        }
    
        public void createTeamDrive() throws Exception {
            TeamDrive teamDriveMetadata = new TeamDrive();
            teamDriveMetadata.setName("Team Drive created with Service Account");
            String requestId = UUID.randomUUID().toString();
            TeamDrive teamDrive = getDriveService().teamdrives().create(requestId, teamDriveMetadata).setFields(FILE_FIELDS).execute();
        }
    
    }
