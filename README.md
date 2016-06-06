# CS673-Project

### Class Documents

[Meeting Minutes](https://drive.google.com/drive/u/0/folders/0B_3YzwwsXS7NZDNHRVhXaENwYVk)

[Quad Reports](https://drive.google.com/drive/u/0/folders/0BwFLA3yVKsC2dmdUTE5xTllXOGM)

[Presentations](https://drive.google.com/drive/u/0/folders/0BwFLA3yVKsC2UGJoUmxOWnR3QWM)

### Helpful Documentation

[BUTeam3 Prod](https://buteam3.herokuapp.com/)

[Spring Docs](https://spring.io/docs)

[Heroku Setup](https://devcenter.heroku.com/articles/getting-started-with-java#introduction)

[Bootstrap](http://getbootstrap.com/css/)

[Stormpath](https://docs.stormpath.com/java/spring-boot-web/)

[JQuery](https://api.jquery.com/)

## Installing Heroku

##### Mac
1. If you don't already have [homebrew](http://brew.sh/) follow the instructions
2. Run `brew install heroku` to install heroku

##### PC
Follow [these](https://toolbelt.heroku.com/windows) instructions

## Setting up Heroku
If you have not already, create a Heroku account [here](https://signup.heroku.com/).

1. Run `heroku login` and enter your account info.
2. Run `heroku git:clone -a buteam3` to clone the heroku project to your local machine
3. Run `cd buteam` to enter the project
4. Run `git remote add origin git@github.com:BUTeam3/CS673-Project.git` to add our remote git repository to the project folder


## Running your dev environment
You'll need to install **[Maven](https://maven.apache.org/)** first.

##### Mac
`brew install maven`

##### PC
[instructions](http://www.mkyong.com/maven/how-to-install-maven-in-windows/)

And install **Postgres**

[mac](https://devcenter.heroku.com/articles/heroku-postgresql#set-up-postgres-on-mac)

[pc](https://devcenter.heroku.com/articles/heroku-postgresql#set-up-postgres-on-windows)

And add the env variable for the postgress db to your local environment.

##### Mac
```
set JDBC_DATABASE_URL='jdbc:postgresql://ec2-54-221-253-87.compute-1.amazonaws.com:5432/d5n7m5f5cvrbr5?user=oopglfyxdggazj&password=pAMFKzXf71sos-Vs-D2XBa4kDI&sslmode=require&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory'
```

##### PC


Now you can run

1. `mvn clean install` to recompile project.
2. `heroku local web` to spin up a local dev server.

You can access your local server at http://localhost:5000

## Pushing to git
When you are satisfied with your branch changes locally you can push to our git using `git push origin <branch name>`.
You can then submit a pull request by clicking **branches**, clicking on your branch, and selecting **New pull request**.
Once a branch has been merged to master you can pull the changes to your local version using `git pull origin master`.

## Deploying to Production
We'll decide as a group if master is ready to deploy to production.
