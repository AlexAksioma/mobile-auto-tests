FROM gradle:8.14.3-jdk21

WORKDIR /app

COPY . .

RUN gradle test --no-daemon --stacktrace --console=plain

CMD ["gradle", "test", "--no-daemon", "--rerun-tasks", "--stacktrace", "--console=plain"]
