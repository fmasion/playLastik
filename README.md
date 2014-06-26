#PlayLastiK

## Playframework 2.2.x / Dead Simple ElasticSearch integration
## Integration with ElasticSearch 1.2.1 
=========

# Why this plugin ?

This plugin integrates play with [elastic4S](https://github.com/sksamuel/elastic4s) which provides an elegant DLS for elastcSearch.

It also extends elastic4s by providing a restClient (maybe this should rather be integrated to elastic4s)

Many libs already exist, most are using ElasticSearch native java client. 

So if you need to use elasticSearch REST api most libs come with their own http client lib. 
	
- this lib uses play WS
Most lib handle json documents but dont use play Json API
- this lib uses play Json api	

###This plugin isn't feature complete it will not cover all elastic4s features (nor all ElasticSearch features)

It fully support indexing, querying, bulk index, manipulating mapping…


# How to install it ?

In your application, add this configuration to the `project/Build.scala` file :

add this dependency for play 2.2.x :

	"playlastik"  % "playlastik_2.10" % "0.90.10.3" 
or

	"playlastik"  % "playlastik_2.10" % "1.2.1.2-SNAPSHOT"


add this resolver :

	resolvers += Resolver.url("Fred's GitHub Play Repository", url("http://fmasion.github.com/releases/"))(Resolver.ivyStylePatterns)


In your application, add to `conf/play.plugins` (or create the file if it dosn't exist) this configuration :

	1500:playlastik.plugin.PlayLastiKPlugin

Finally in the `conf/application.conf` you can configure some elements of vert.x  and for clustering. The cleanest way is to add this conf in additional files so add in `conf/application.conf` these lines :
	
	include "playlastik.conf"
	
	# Logger provided to playLastiK :
	logger.playlastik=INFO

Create `conf/playlastik.conf` you can configure some elements of vert.x :
	
	playLastiK {
	
		# Dev Mode launches a local elasticSearch instance
		isDevMode=true
		
		# Define a cluster name so you can avoid clustering with other persons while in dev mode
		# Default is "playLastiK"
		cluster.name="elasticsearchDev1"
		
		# in Dev Mode you may want a fresh local elasticSearch instance on restart
		# only clean in dev mode : isDevMode=true
		# cleanOnStop=true
	
		# elasticsearch url default to http://localhost:9200
		url="http://localhost:9200"
	
		# Athentification of the http client
		# schemes are : BASIC, DIGEST, KERBEROS, NONE, NTLM, and SPNEGO
		# defaults to NONE
		authentication=NONE
		#authentication.user=""
		#authentication.pass=""
	
	}
  

# License

© F.Masion

This project is published under the Apache License v2.0.

You may obtain a copy of the License at [http://www.apache.org/licenses/LICENSE-2.0] (http://www.apache.org/licenses/LICENSE-2.0).