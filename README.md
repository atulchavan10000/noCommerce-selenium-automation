How do you switch between different environments

    data: when you pass -Denv=qa in command line, the selected env is qa, and we are also accessing data for specific environment
            from file env-test-data.properties
    
    config: to select the env url, we have config manager, the same environment variable -Denv=qa, will be read from config manager
               and we can pick a url according to passed paramter from file: default-config.properties


How a browser change is configured:
    
    pass the browsername like below
         -Dbrowser=chrome
    The BaseTest class is reading this from system properties, if you dont provide any, default "chrome" will be picked
    Now once this property is read from BaseTest, the setUp() method will create the instance of provided driver like below
        DriverManager.setWebDriver(DriverFactory.createInstance(DEFAULT_BROWSER));
    DriverFactory has capability to create web driver instance of any browser
    To make this WebDriver thread safe, we have used DriverManager.
    The setup() method also check if there is an already existing instance of Webdriver for current thread
    if it does, then nothing new is created.
    

Reporting
    
    I've used Extent Reports using TestNg listener event.
    The Listeners class is implementing ItestListener interface and overriding methods like onTestPass, onTestFailure   
    On failure, the listener also adds a screenshot to Extent report    
        reports are saved on this path
        //reports//index.html


logging
    
