Dingo Application
==================
Microservice is an architecture that is quickly gaining attention. Though it's very much possible to hand code the services and supporting components from scratch, a lot of it is already available as reusable components. Adopting industry standard components not only help keep the code clean & to a miniumum, but also naturally helps solve a varity of common problems encountered in arena of microservice architectures, without having to reinvent the wheel. Furthermore it helps to keep the pace with the best in the industry while enabling one to take advantage of the new developments. 

About Microservices in brief
-----------------------------
While both Microservice & SOA are both Service Based Architectures, they are slightly different in that Microservice usually follow Service Choreography rather than Service Orchestration. Services are more fine grained and are usually known to do the one thing that it does really  well. Control flow in case of Microservices are more workflow based. On the other hand, factors discouraging one to make to services further more atomic may be spanning transactions and performance bottlenecks due to distributed nature of it's services. Some of the key components that make up a MicroService Infrastructure are described below in this article. Microservices make Heterogeneous Interoperability possible and is a natural favourite architectural design pattern of most polyglot programmers, enabling him to use the right tool for a given problem.

Source:
-------
https://github.com/technicalyorker/source/tree/master/Spring/dingo
    
About Dingo:
------------
Dingo is the name of an Australian Wild Canine.The dingo is the largest terrestrial predator in Australia, and plays an important role as an apex predator.

About Application:
------------------
Dingo is a demo application that manages customer and products. 
Dingo makes use of Spring Cloud and primarily focuses on it's relevance in Microservice (or Microservice like) applications. Following are some of it's modules in alphabetic order:
customer-master: common reusable artifacts for all customer related modules<br>
customer-repo: The customer Data service. Uses RDMS as datastore.<br>
customer-service: The customer Service<br>
dingo-api-gateway: API Gateway<br>
dingo-common: Class Artifacts shared by the whole application.<br>
dingo-config: Configuration server for the application.<br>
dingo-dashboard: Hystrix Dashboard<br>
dingo-discovery: Eureka (Discovery) Server<br>
product-master:	artifacts common to all products<br> 
product-service: Service that maintains Products. Uses Mongodb, document-based db for datastore.- yet to be done completed<br>  
resources: All resource files.<br>
parent: will co-ordinate all the maven modules.- yet to be done completed<br>

Zuul MicroProxy
----------------
Zuul Microproxy plays the role of an API Gateway or MicroProxy. All requests first hit the Zuul Microproxy before it is either routed or filtered based on the use case. The catch is that the routing logic with Zuul is as simple as just adding a key value pair in the application.properties or bootstrap.properties file.<br>
Following excerpt indicate that any request which hits /customer/\*\* should be handled by the customer-service. This is again made complete by the service discovery made possible by discovery server.<br>
zuul.routes.customer.path=/customer/\*\*<br>
zuul.routes.customer.serviceId=customer-service<br>
Source: https://github.com/technicalyorker/source/blob/master/Spring/dingo/dingo-api-gateway/src/main/resources/bootstrap.properties
<br>
<br>
This translates into http://customer-service/<br>
e.g. The above two line configuration, in our context this is equallent to having to write code that says all requests that come to http://localhost:8080/customer/ 
<br> is to be routed and handled by service running at http://localhost:8002/.<br>
<br>
Usecases such as Security (e.g. using SSL or Security token) & Routing can be handled using of ZuulFilter.

Service Discovery:
--------------------
With Service Discovery, Servers/Service-End-Points have no need to be identified by http://host:port, but rather by their Service Names. 
e.g. 
<br>http://localhost:8002/service/platinums
will be represented as
<br>http://customer-service/platinums
<br>Even in case of a cluster where traditionally the services were managed by a cluster address or load balancer address, here we deal with only Services and their Service Names. A developer is totally ignorant of the host/port or doesn't bother whether the code is going to be part of a Standalone Service Instance or (Hetrogeneous or Homogeneous) Cluster. Developer writes his code to target "Service Names" which are resolved by Discovery Server at runtime. Thus the Code or Properties will not have to define this information. In our case we use the Discovery(Eureka)Server and DiscoveryClients to registered services with the Directory server. This works well with and in tandem with RestTemplate or FeignClients to seamlessly integrate the application with other services in the Microservice infrastructure Stack.
<br>e.g. of Service Discovery: using RestTemplate
<br>template.exchange("http://customer-repo/data/platinums", HttpMethod.GET, null, d).getBody()
<br>Source: https://github.com/technicalyorker/source/blob/master/Spring/dingo/customer-service/src/main/java/com/technicalyorker/dingo/customer/CustomerServiceApplication.java
<br>
Screenshot of Service Discovery on a Eureka Server Dashboard:
![alt tag](https://github.com/technicalyorker/source/blob/master/Spring/dingo/resources/eureka.png)

Ribbon Load Balancer
--------------------
Netflix Ribbon is a Software component for Loadbalancing, that allows us to implement our LB algorithm for a particular service(s) of choice with the default one being Round-Robin. Again the code is targetted to Service Names and developer doesn't bother if it is going to be a Single Node  or an Active-Active setup, while writing his code. 
@LoadBalanced Source: <br>
https://github.com/technicalyorker/source/blob/master/Spring/dingo/customer-service/src/main/java/com/technicalyorker/dingo/customer/CustomerServiceApplication.java 

Here is another sample project demonstrating Software Load Balancing with Ribbon:
https://github.com/technicalyorker/source/tree/master/Spring/ecomm

FeignClients
-------------
Majority situations demand same contracts for REST Services & its Clients.
<br>e.g.<br>In our case, interface: CustomerController.java (https://github.com/technicalyorker/source/blob/master/Spring/dingo/customer-master/src/main/java/com/technicalyorker/dingo/customer/controller/CustomerController.java) is reused by Data Service & Consumer Service
With FeignClients developers virtually write no any extra client code to interface with the Service End-Points. Simply extending the CustomerController which has the controller contract defintions and indicating to it the destination service is enough for it to be able to talk REST to the specific end points.<br>
https://github.com/technicalyorker/source/blob/master/Spring/dingo/customer-service/src/main/java/com/technicalyorker/dingo/customer/client/CustomerRepoClient.java<br>
To see usage: https://github.com/technicalyorker/source/blob/master/Spring/dingo/customer-service/src/main/java/com/technicalyorker/dingo/customer/service/CustomerServiceImpl.java<br>
Additionally in a clustered setup, the call to the underlying services happens in a Loadbalanced fashion.
<br>
Configuration Server:
--------------------
Generally properties are used to externalize attributes (environment specific and otherwise), are commonly stored in properties file(such as application.properties) or a Spring Profile is used to differentiate one environment from other. While this works, Configuration servers helps maintain the configuration in Github or bitbucket. Properties are thus centralized and one can override these properties by defining them in the application(or bootstrap).properties file. Private repositories ensure that the environment specific properties such as security tokens such as keys or signatures are not available to developers who don't require to see them<br>
The properties in the configuration are also refershable, i.e. in a production like environment there is no application downtime or better still no release is required to refresh the properties. Spring actuator exposes HTTP End Point mappings for refresh. The properties are recognized by service names.
https://github.com/technicalyorker/spring-cloud-config

Hystrix Circuit Breaker 
-----------------------
When the Destination Service is down for any reason, it is usually desirable to show an appropriate message to the end user rather than exception stacktrace. A lot of times it comes in the form of Unplanned Outages. Hystrix Circuit Breaker is a Soft-Switch that is applied to the response when the response displays undesirable state. This fallback mechanism is commonly known by the name "Circuit Breaker Pattern":
https://martinfowler.com/bliki/CircuitBreaker.html
![alt tag](https://github.com/technicalyorker/source/blob/master/Spring/dingo/resources/ckt%20breaker.png)

Hystrix Dash Board
------------------
Hystrix Dash Board gives a sense of health of the service end point. Stream samples can be analysed on the Dashboard. It also gives a insight into how many requests have successfully been processed and how many have fallen back due e.g. due to Service unavailability.
![alt tag](https://github.com/technicalyorker/source/blob/master/Spring/dingo/resources/hystrix-dashboard.png)

RepositoryRestResource
-----------------------
RepositoryRestResource creates RESTful end points with no coding. In dingo context it is used to demonstrate CRUD Operations on Customer entity in the project customer-repo.
This even comes HATEOAS (Hypermedia As The Engine Of Application State) Enabled, which is a typical quality of a RESTful Webservices.
![alt tag](https://github.com/technicalyorker/source/blob/master/Spring/dingo/resources/restrepositoryresource.png)

Docker (Compose) Containers
---------------------------
The whole application can be contained within Docker containers and can streamline running the instances with Docker Composer. Currently for testing the product-service is the only service which is Docker enabled and will be soon extended to other modules as well.

Spring Cloud Stream
-------------------
One of the major problems with Microservice applications is transactions. XA Transactions doesn't solve all the problems relating to transactions particularly Data Consistency. Situations where HeuristicRollbackException might require the services to re-issue the request or even worse HeuristicMixedException would require manual intervention to correct the data or rendering data useless. In practical terms Eventual Consistency could be applied in a lot of situations. One way of achieving this is by the use of a Messaging Channel which Guarantees Delivery. Spring Cloud Stream could be applied in scenarios like this. In situations where business transactions are idempotent in nature (or designed so) can potentially adopt Eventual Consistency with the help of a Spring Cloud Stream Messaging Input/Output channels.
