# Use the Node.js image as the base for the build stage
FROM node:18 AS build-stage

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install project dependencies
RUN npm install

# Copy the entire project into the container
COPY . .

# Build the Angular application in production mode
RUN npm run build --prod

# Use the nginx image as the base for the production stage
FROM nginx:1.25-alpine AS production-stage

# Copy the built Angular application to the nginx HTML directory
COPY --from=build-stage /app/dist/client /usr/share/nginx/html

# Optionally copy a custom nginx configuration file
# COPY nginx.conf /etc/nginx/nginx.conf

# Expose the port on which nginx will serve the application
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]