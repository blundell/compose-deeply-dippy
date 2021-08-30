Instructed from https://medium.com/mobile-app-development-publication/upload-to-mavencentral-made-easy-for-android-library-30d2b83af0c7


### How to release

1) Ensure the version is updated in dippy/build.gradle (ext)
2) Run the gradle command: ` ./gradlew dippy:publishReleasePublicationToSonatypeRepository`
3) A jar will be made and uploaded to Mavens staging server
4) Go to https://s01.oss.sonatype.org/#stagingRepositories select the staging build and press 'Close'
5) After closing, wait a minute, then refresh, then press 'Release'
6) 15-ish minutes later, that version will be live! (Double check here: https://repo1.maven.org/maven2/io/github/)
