# Release Steps

To publish a new version of the MOEA Framework or related project:

1. Create and merge a PR for the new version.  Be sure to update `build.properties`, `releaseNotes.md`, and `README.md`.
2. Trigger the [staging workflow](https://github.com/MOEAFramework/MOEAFramework/actions/workflows/staging.yml) to
   stage the release artifacts.
3. Update `pom.xml` in this repository with the new version and verify all tests pass.
4. Release the Maven artifacts on http://oss.sonatype.org.
5. Publish the GitHub release.
6. Publish the Website update.

## GPG Signing Keys

Maven artifacts are signed with our [GPG-generated key](https://keyserver.ubuntu.com/pks/lookup?search=04fb5ffc82aedf3a&fingerprint=on&op=index).

As these keys expire after a few years, we may need to periodically generate a new key and update the corresponding GitHub Actions secrets.

```bash
gpg --gen-key
gpg --keyserver keyserver.ubuntu.com --send-keys <keyId>
gpg --armor --export-secret-key <keyId>
```
