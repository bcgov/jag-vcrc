## Templates to create openshift components related to jag-vcrc api deployment

### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod
   ``oc process -f jag-vcrc.yaml --param-file=jag-vcrc.env | oc apply -f -``


