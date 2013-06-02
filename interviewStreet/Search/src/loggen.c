#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<string.h>
 
#define INCREMENT 5 
#define BURSTPROB 0.2
 
int main(int argc, char *argv[]) 
{
    int lines_count;
    time_t cur_timestamp, start_timestamp;
    char* formatted_time;
    int i;
    int http_codes[] = {200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 200, 404, 404, 500, 301, 301, 302, 503};
    char *dummy_urls[] = {
        "/challenges/solve/submit", 
        "/recruit/test/start",
        "/questions/view/all?permissions=all", 
        "/user/setpassword?id=6&password=coffeemug", 
        "/challenges/solve/view",
        "/challenges/leaderboard/view",
        "/users/signout",
        "/users/signin?uid=5&pwd=yeahright",
        "/procure/items?name=pepsi&quantity=a-lot&volume=2L",
        "/", "/", "/", "/", "/", "/"
    };
    if(argc != 2) {
        printf("Usage: %s <lines-count>\n", argv[0]);
        exit(0);
    }
    start_timestamp = time(NULL);
    lines_count = atoi(argv[1]);
 
    cur_timestamp = start_timestamp;
 
    srand(time(NULL));
 
    for(i=0; i<lines_count; i++) {
        if(drand48() > BURSTPROB) {
            cur_timestamp += INCREMENT * drand48();
        }
        formatted_time = ctime(&cur_timestamp);
        formatted_time[strlen(formatted_time) - 1] = '\0';
        printf("%s %d %d %s\n", 
                formatted_time,
                http_codes[(int)(drand48() * (sizeof(http_codes) / sizeof(int)))], 
                300 + (int)(drand48() * 300),
                dummy_urls[(int)(drand48() * (sizeof(dummy_urls) / sizeof(char*)))]);
    }
}
