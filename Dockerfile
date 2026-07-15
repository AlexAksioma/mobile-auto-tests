FROM gradle:8.14.3-jdk21

WORKDIR /app

COPY . .

RUN gradle test --no-daemon

CMD ["gradle", "test", "--no-daemon"]