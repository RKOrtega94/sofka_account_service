FROM ubuntu:latest
LABEL authors="rkort"

ENTRYPOINT ["top", "-b"]