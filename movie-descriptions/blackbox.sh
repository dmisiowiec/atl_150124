#!/bin/bash

for i in {1..5}; do
  echo "Attempt $i"
  if curl -s -o /dev/null -w "%{http_code}" http://localhost:8082/actuator/health | grep "200" > /dev/null; then
    exit 0
  fi
  sleep 5
done
echo "Blackbox test failed!"
exit 1
