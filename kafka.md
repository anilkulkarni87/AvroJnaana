## Project setup
Install the confluent kafka on your machine. This is the most easiest way as it comes packaged with all services like schema registry, connector. 

[Installation instructions](https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html){:target="_blank"}

 ``` 
 confluent local services start 
 ```

 - Run the `ProducerDemo.java`

 - Run the `ConsumerDemo.java`

 Please take a look the output.

 ``` 
 confluent local services stop 
 ```

 ## Things to Observe:

 - The producer uses the java classes generated from the avdl files. 
 - The consumer actually decrypts the data it reads from the topic. 
 - If you use the kafka consumer cli you will see encrypted data. 
 - The custom logical types along with conversions defined make this happen. The only way to decrypt is by reading the data by using java classes generated.
 - If the message on the topic is intercepted, it cannot be decrypted.