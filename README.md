## Notes
0. AWS secrets are available under `~/.aws/credentials` after you do `aws-okta select **`
1. Set the region, since we're running it on a local machine: https://stackoverflow.com/questions/58022282/spring-boot-cannot-start-with-aws-starter-dependency
2. Set stack autoconfiguration to `false`, since there is no cloud formation: https://stackoverflow.com/questions/54879212/unable-to-use-spring-cloud-to-connect-with-aws-ses
3. This works: https://cloud.spring.io/spring-cloud-aws/spring-cloud-aws.html#_uploading_with_the_transfermanager , but is deprecated