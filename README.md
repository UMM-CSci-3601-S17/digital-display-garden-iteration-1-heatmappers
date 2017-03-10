<h3> iteration 1: HeatMappers <br/>
Shawn, Brian, Jacob and Spencer. 
</h3> 

<br/>

Stories <br/>
Get excel into usable format<br/>
<p>
This was the main attraction of our iteration. We were able to use an excel file and take it so that we can push the data into our database. 
The files that contain this process it the Converter class which actually changes the excel into usable form. The Mapping class then pushes the converted file into mongo.
</p> <br/>
Apache poi <br>
This was a library we had to import to be able to use excel files. The configuration is in build.gradle on the server side. 
</p>
<br/>
Warnings <br/>
<p>For now the excel file used is an absolute path Also the output to the database does not follow standard committee rules because we were not aware of this.
</p><br/>

Be able to submit comments<br/>
&&Store comments in our database: <br/>
<p>Our iteration group decided to implement disqus as our comment system. This did not work very well as run into a couple of problems. The problems were that because angular is a single page Web application the comments are not unique on each page.
<br/>
If you have any questions about disqus please ask Spencer as he might know more about the issues. The implementation is in index.html  </p> <br/>

Generate QR code for website:<br/>
This story took into in fact that we would have a unique Url to be able to generate a unique qr code for it we didn't reach this story because of that fact.<br/>

Other features: <br/>
Basic angular filtering on beds page., and links to QR app scanners for iPhone and Android on home page.

Side note: <br/>
For the demo we had to change our webpack.prod.js on the client side from 
'API_URL': JSON.stringify('http://localhost:4567/api/') }) to 
'API_URL': JSON.stringify('http://<neoprene:4567/api/')}) to see our database on other computers. <br/>

Travis.CI <br/>
Our Travis integration never passed for some reason that we were never to figure out even when. Our test passed.