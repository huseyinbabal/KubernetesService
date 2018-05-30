### Description
This is a REST API to scale specific deployment by name. I assume you already Kubernetes cluster setup.
You have valid `.kube/config` file on your home directory. I used DefaultKubernetesClient to make it simple. 

### How to run
`mvn clean spring-boot:run`

### Testing
After you have kubernetes cluster and you have made at least one deployment to default namespace.
Just call `http://localhost:8080/app/scale?name=<your_app_name>&replicas=3`