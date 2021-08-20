.PHONY: 

clean:
	-rm -rf resources/public/js/*	

watch-ui:
	npx shadow-cljs watch app
    
release-ui: clean
	npx shadow-cljs release app

deploy :
	scp -r -i ~/.ssh/MyFreeAWSBox.pem ./resources/public/js/main.js ./resources/public/css/styles.css admin@ec2-184-72-157-253.compute-1.amazonaws.com:/home/admin/volley
