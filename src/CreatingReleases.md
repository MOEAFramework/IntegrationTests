# Release Guide for the MOEA Framework
   
## One Time Setup

The following generates a new signing key.  These keys are valid for several years, but if expired or lost we can always generate new keys.

1. Generate a new GPG key:
   ```
   gpg --gen-key
   #    Name: MOEAFramework
   #    Email: admin@moeaframework.org
   #    Password: Leave blank / unprotected
   ```
2. Publish key to key server:
   ```
   gpg --keyserver keyserver.ubuntu.com --send-keys <keyId>
   ```
3. Create base64 encoded version of the key
   ```
   gpg --armor --export-secret-key <keyId> | base64
   ```
4. Update the `GPG_SIGNING_KEY` [secret on GitHub Actions](https://github.com/MOEAFramework/MOEAFramework/settings/secrets/actions) with the new value

## Updating Dependencies

To update dependencies, first update the version numbers in `META-INF/build.properties`.  If adding or removing dependencies, also make the necessary
changes to `build.xml`, `test.xml`, and `pom.xml`.

Run the `update-libraries` Ant target to update the JAR files.  Push these changes into the repo.

## Publishing New Version

1. Create a PR to publish the new version
   1. Update version number in `META-INF/build.properties` and `README.md`
   2. Update version number, releate date, and changelog in `docs/news.md`
   3. Update `website/xslt/archive.xml` with links to older versions
   4. Update copyright notices as required.
2. Merge PR once all CI tests pass
3. Trigger the [staging release](https://github.com/MOEAFramework/MOEAFramework/actions/workflows/staging.yml) to prepare all deployment files
4. Publish the Maven package
   1. Download the Maven bundle from the staging release and upload as an "artifact bundle" to `oss.sonatype.org`
   2. Edit `pom.xml` in this repo with the new version number and verify the CI tests pass
   3. Release the new version
5. Publish GitHub release - https://github.com/MOEAFramework/MOEAFramework/releases
6. Merge the Website update - https://github.com/MOEAFramework/Website/pulls

## Building and Publishing Book

1. GitHub Actions handles compiling the Latex.  For local development use [TexLive](https://www.tug.org/texlive/windows.html).
2. Makes changes to https://github.com/MOEAFramework/MOEABook
3. GitHub Actions produces an artifact with the new book PDF, preview PDF, and supplemental materials Zip
3. If the supplemental materials Zip ever changes (very infrequently)
   1. Upload new version to Google Drive, share via link, and get the download URL
   2. Create a new bit.ly shortened URL
   3. Update book with the new link
4. If the PDF ever changes
   1. Log into lulu.com
   2. Edit the Beginner's Guide to the MOEAFramework product and replace the PDF
   3. Publish!

## Old Release Notes

1. Update `META-INF/build.properties` with new version number.
2. Update `news.md` with version number, release date, and release notes.
3. Update `website/xslt/archive.xml` with links to the last version.
4. If necessary, update the copyright year.
   1. Source code is updated using the `update-header` Ant task in `auxiliary/checkstyle/build.xml`.
   2. Search for the previous year and update any other locations
5. Ensure all test runs pass.
   1. `ant -f text.xml`
   2. `ant -f test.xml build-maven-tests & cd build & mvn test`
6. Run all of the `package-*` Ant tasks in `build.xml`.
7. Create a new Github Release using a tag in the format `vX.XX`.
   1. Copy the release notes from `news.md`
   2. Publish the following files in the `dist` folder:
      - `MOEAFramework-X.XX.tar.gz`
      - `MOEAFramework-X.XX-Demo.jar`
      - `MOEAFramework-X.XX-Source.tar.gz`
8. Publish the Maven package:
   1. Run the `package-maven` Ant task in `build.xml`.
   2. Visit `oss.sonatype.org` and login
   3. Create a new Staging Upload, select Artifact Bundle, and upload `*-bundle.jar` from `maven/`
   4. Verify the package by editing `pom.xml` (in this repo) and setting the new version number
   5. If CI is passing, release the new version
   6. Update README.md with the new Maven link and example
9. Publish the new website:
   1. Run the `package-website` Ant task in `build.xml`.
   2. Checkout the latest `MOEAFramework\Website` repo and switch to the `gh-pages` branch.
   3. Copy the entire contents of the `build` folder into the `Website` repo.
   4. Commit and push.
   5. Verify the download links work on the website.
10. Publish new version of book
