# Release Steps

To publish a new version of the MOEA Framework or related project:

1. Create and merge a PR for the new version.  Be sure to update `build.properties`, `releaseNotes.md`, and `README.md`.
2. Trigger the [staging workflow](https://github.com/MOEAFramework/MOEAFramework/actions/workflows/staging.yml) to
   stage the release artifacts.
3. Update `pom.xml` in this repository with the new version and verify all tests pass.
4. Release the Maven artifacts on http://oss.sontatype.org.
5. Publish the GitHub release.
6. Publish the Website update.

## GPG Signing Keys

The following generates a new signing key.  These keys are valid for several years, but if expired or lost we can always generate new keys.

1. Generate a new GPG key:
   ```
   gpg --gen-key
   #    Name: MOEAFramework
   #    Email: admin@moeaframework.org
   #    Password: <passpharse>
   ```
2. Publish key to key server:
   ```
   gpg --keyserver keyserver.ubuntu.com --send-keys <keyId>
   ```
3. Create base64 encoded version of the key
   ```
   gpg --armor --export-secret-key <keyId> | base64
   ```
4. Update the GitHub Actions secrets