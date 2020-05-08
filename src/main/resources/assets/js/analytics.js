(function($) {
	var saleReportChart = new Chart(document.getElementById('monthlyOrderReport').getContext('2d'), {
		type : 'line',
		data : {
			labels : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12' ],
			datasets : [ {
				label : 'Monthly Sale Report',
				data : [ 12, 19, 3, 5, 2, 3, 12, 19, 3, 5, 2, 3 ],
				backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
						'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
						'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
						'rgba(255, 159, 64, 0.2)',
						'rgba(200, 102, 255, 0.2)',
						'rgba(222, 102, 255, 0.2)',
						'rgba(21, 102, 255, 0.2)',
						'rgba(17, 102, 255, 0.2)',
						'rgba(134, 102, 255, 0.2)',
						'rgba(221, 102, 255, 0.2)'],
				borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)',
						'rgba(255, 159, 64, 1)',
						'rgba(200, 102, 255, 1)',
						'rgba(222, 102, 255, 1)',
						'rgba(21, 102, 255, 1)',
						'rgba(17, 102, 255, 1)',
						'rgba(134, 102, 255, 1)',
						'rgba(221, 102, 255, 1)'],
				borderWidth : 1
			} ]
		},
		options : {
			legend: {
	            labels: {
	                // This more specific font property overrides the global property
	                fontSize: 20
	            }
	        },
			scales : {
				yAxes : [ {
					ticks : {
						beginAtZero : true,
						fontSize: 40
					}
				} ],
				xAxes : [ {
					ticks : {
						fontSize: 40
					}
				} ]
				
			}
		}
	});
	
	
	function init() {
		
		loadSaleReport(saleReportChart, 2019);
		
		loadTop5MostRatedProducts();
		
		loadTop5MostBuyUsers();
		
		
	}
	
	function loadSaleReport(chart, year) {
		$.get( "/analytics/orders/monthly-sale-report", { year } )
		  .done(function( data ) {
			  chart.data.datasets[0].data = data.map(i => i.amount);
			  chart.update();
		  });
	}
	
	function loadTop5MostRatedProducts() {
		$.get( "/analytics/products/top-5-most-rated" )
		  .done(function( data ) {
			let html = "";
			  
			for (let p of data) {
				let string = '<div class="col-md-6 col-xl-4">' + 
								'<div class="card m-2 widget-content">' + 
									'<div class="widget-content-outer">' +
										'<div class="widget-content-wrapper">' + 
											'<div class="widget-content-left">' + 
												'<div class="widget-heading">' + p.name + '</div>' + 
												'<div class="widget-subheading">Average Point: ' + Number.parseFloat(p.avg).toFixed(2) + '</div>' + 
											'</div>' + 
											'<div class="widget-content-right">' + 
												'<div class="widget-numbers text-success">' + p.numberOfRates + '</div>' + 
											'</div>' +
										'</div>' +
									'</div>' + 
								'</div>' + 
							'</div>';
				html = html.concat(string);
			}
			$(".top5MostRatedProducts").html(html);
		  });
	}
	
	function loadTop5MostBuyUsers() {
		$.get( "/analytics/users/top-5-most-buy")
		  .done(function( data ) {
			let html = "";
			  
			for (let u of data) {
					let string = '<div class="col-md-6 col-xl-4">' + 
									'<div class="card m-2 widget-content">' + 
										'<div class="widget-content-outer">' +
											'<div class="widget-content-wrapper">' + 
												'<div class="widget-content-left">' + 
													'<div class="widget-heading">' + u.name + '</div>' + 
													'<div class="widget-subheading">Total products: ' + u.numberOfProducts + '</div>' + 
												'</div>' + 
												'<div class="widget-content-right">' + 
													'<div class="widget-numbers text-success">' + new Intl.NumberFormat('vi', { style: 'currency', currency: 'VND' }).format(u.totalBudget) + '</div>' + 
												'</div>' +
											'</div>' + 
										'</div>' +
									'</div>' +
								'</div>';
					html = html.concat(string);
			}
			$(".top5MostBuyUsers").html(html);
		  });
	}
	
	init();
	
	

})(jQuery);