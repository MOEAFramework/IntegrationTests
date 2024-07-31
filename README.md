# Integration and Performance Testing

This repository contains integration tests and performance benchmarks for the
[MOEA Framework](https://github.com/MOEAFramework/MOEAFramework).

## Release Steps

1. Create and merge a PR for the new version.  Be sure to update `build.properties`, `releaseNotes.md`, and `README.md`.
2. Trigger the [staging workflow](https://github.com/MOEAFramework/MOEAFramework/actions/workflows/staging.yml) to
   stage the release artifacts.
3. Update `pom.xml` in this repository with the new version and verify all tests pass.
4. Release the Maven artifacts on http://oss.sontatype.org.
5. Publish the GitHub release.
6. Publish the Website update.
