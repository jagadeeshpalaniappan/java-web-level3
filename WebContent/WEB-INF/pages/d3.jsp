<!DOCTYPE HTML>

<html>

<head>

<!--
<script src="http://d3js.org/d3.v3.min.js" charset="utf-8"></script>
-->

<script src="js/d3.v3.min.js"></script>

</head>


<body>

		
	<script>
	
			var dataArray = [10,20,40,60,90];  //domain values
			var width = 1000;
			var height = 1000;
			
			var widthScale = d3.scale.linear()	//return linear values
									.domain([0,90])
									.range([0,width]);
			
			var colorScale = d3.scale.linear()	//return linear values
									.domain([0,90])
									.range(["yellow","green"]);
			
			
			var axis = d3.svg.axis()
								.ticks(15)
								.scale(widthScale);
			
			var canvas = d3.select("body")
										.append("svg")
											.attr("width",width)
											.attr("height",height)
										.append("g")
											.attr("transform","translate(50,50)");
										
										
										
			var barChart = canvas.selectAll("rect")      	//returning empty selection
										.data(dataArray)            //bounded our data to our empty selection
										.enter()
											.append("rect")
											.attr("width",function(d){return widthScale(d);})
											.attr("height",50)
											.attr("x",0)
											.attr("y",function(d,i){return i*100;})
											.attr("fill",function(d){return colorScale(d);});
											
			
			var barChartAxis =canvas.append("g")
								.attr("transform","translate(0,500)")
								.call(axis);								
																	
	</script>


</body>


</html>