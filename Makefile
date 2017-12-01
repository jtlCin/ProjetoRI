package=busca
packageC=$(shell echo $(package) | tr '.' '/' )
classrun=Ranking
all: compile run

compile:
	@mkdir -p bin/$(packageC)
	@javac -encoding "ISO-8859-1" src/$(packageC)/*.java -d bin
run: 
	@cd bin; java $(package).$(classrun)
clean:
	@rm -rf bin/*
