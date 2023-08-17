# node18을 쓰고 build-stage로 명명한다.
FROM node:18 as build-stage
# 작업 공간을 /app으로 정한다.
WORKDIR /app
# package*.json으로 되어있는 모든 것을 복사한다.(설정 파일이니까)
COPY package*.json ./
# 설치하고
RUN npm install
#복사하고
COPY . .
# 빌드한다.
RUN npm run build


# 배포할 때 nginx를 쓴다고했는데, nginx:stable-alpine을 사용하면 공식 nginx를 사용하게 된다.
# 즉 배포를 할 때 포트를 80밖에 쓸 수 없게 된다.
FROM nginx:stable-alpine as production-stage
# 작업 공간이 app인데 app이 없을 수도 있으니까 만들어준다.
RUN mkdir /app
# nginx:stable-alpine으로 생성되는 etc/nginx/conf.d 안에 있는 default.conf를 삭제하는데
# nginx 설정을 내가 해줄 것이기 때문에 공식 nginx 설정을 지운다고 생각
RUN rm /etc/nginx/conf.d/default.conf
# 내가 만든 nginx.conf 파일을 넣어준다. 경로에서도 알 수 있지만 DockerFile과 nginx.conf 파일은 같은 위치에 있다.
COPY nginx.conf /etc/nginx/conf.d/default.conf
# build-stage 에서 만들어진 app안의 dist(npm run build를 하면 dist 폴더가 생성된다.)를 복사하여
# /usr/share/nginx/html 안에 넣어준다.
# 여기서 /usr/share/nginx.html은 내가 만든 nginx.conf 파일의 root와 동일해야 한다.
COPY --from=build-stage /app/dist /usr/share/nginx/html
# 내가 front에서 사용할 포트 번호를 입력해준다.
EXPOSE 8080  
CMD ["nginx", "-g", "daemon off;"]




