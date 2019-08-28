"""
HW4: Movie Trivia

This homework deals with the following topics:
- Dictionaries
- Sets
- Databases (using dictionaries -- not too far from how they really work!)
- Test driven development (TDD)

In this HW, we will deal with representing movie data using dictionaries,
with the goal of answering some simple movie trivia questions.
For example, “what is the name of the movie that both Tom Hanks and
Leonardo DiCaprio acted in?”.  Or, “what are the movie ratings for Rocky I?”

We will use 2 dictionaries. The first corresponds to information about an actor
and all the movies that he/she has acted in.  The second corresponds to information
about the critics’ score and the audience score from https://www.rottentomatoes.com/,
about the movies.

Given that information, we will then want to answer some typical movie trivia
questions.
"""

#Use these first 2 functions to create your 2 dictionaries
import csv
def create_actors_DB(actor_file):
    """
    Creates a dictionary keyed on actors from a text file.
    """
    
    f = open(actor_file)
    movieInfo = {}
    for line in f:
        line = line.rstrip().lstrip()
        actorAndMovies = line.split(',')
        actor = actorAndMovies[0]
        movies = [x.lstrip().rstrip() for x in actorAndMovies[1:]]
        movieInfo[actor] = movies
    f.close()
    
    return movieInfo

def create_ratings_DB(ratings_file):
    """
    Creates a dictionary from the movie ratings (from rotten tomatoes) in a csv file.
    """
    
    scores_dict = {}
    with open(ratings_file, 'r', encoding = 'utf-8') as csvfile:
        reader = csv.reader(csvfile)
        reader.__next__()
        for row in reader:
            scores_dict[row[0]] = [row[1], row[2]]
            
    return scores_dict


def insert_actor_info(actor, movies, actordb):
    """
    Insert movies to corresponding actors or add new actors with his/her movies.
    """
    if type(actor) != str:
        raise TypeError('Actor must be typed as a string!')  # make sure actor is a string
    if type(movies) != list:
        raise TypeError('Movies must be typed as a list!')  # make sure movie is a list
    actor = input_trans(actor)  # solve case sensitivity and white space

    if actor in actordb:
        actordb[actor] += movies  # if the actor already exists, add movies
    else:
        actordb.update({actor: movies})  # if the actor is new, update the database


def insert_rating(movie, ratings, ratingsdb):
    """
    Insert movies and update the ratings.
    """
    if type(movie) != str:
        raise TypeError('Movie must be typed as a string!')  # make sure movie is a string
    if type(ratings) != list:
        raise TypeError('Ratings must be typed as a list!')  # make sure rating is a list
    movie = input_trans(movie)  # solve case sensitivity and white space
    ratingsdb[movie] = ratings  # change current movie rating or add new movie rating

def select_where_actor_is(actor_name, actordb):
    """
    Given an actor, return the list of all movies.
    """
    if type(actor_name) != str:
        raise TypeError('Actor must be typed as a string!')  # make sure actor is a string
    actor_name = input_trans(actor_name)  # solve case sensitivity and white space
    if actor_name in actordb:
        return actordb[actor_name]  # if the actor is in database, return a list of his/her movies
    else:
        return []  # if the actor is not in database, return an empty list

def select_where_movie_is(movie_name, actordb):
    """
    Given a movie, return the list of all actors in that movie.
    """
    if type(movie_name) != str:
        raise TypeError('Movie must be typed as a string!')  # make sure movie is a string
    movie_name = input_trans(movie_name)  # solve case sensitivity and white space
    actor = []
    for i in actordb:
        for j in actordb[i]:
            if movie_name == j:  # find the movie in the dictionary
                actor.append(i)  # add the current actor to the list
    return actor  # if no actor is in the database, return an empty list




def select_where_rating_is(comparison, targeted_rating, is_critic, ratingsdb):
    """
    It returns a list of movies that satisfy an inequality or equality,
    based on the comparison argument and the targeted rating argument
    """

    if comparison not in ['<', '>', '=']:
        raise TypeError('comparison should only be "<", ">" or "=" ')  # make sure comparison won't be other strings
    if type(targeted_rating) != int:
        raise TypeError('targeted_rating should be typed as an integer!')  # make sure targeted_rating is int
    if type(is_critic) != bool:
        raise TypeError('is_critic should be typed as Boolean!')  # make sure is_critic is a Boolean
    if targeted_rating < 0 or targeted_rating > 100:
        return []  # make sure targeted_rating is with the range 1-100

    rating = []
    if comparison == '<':
        for i in ratingsdb:
            if is_critic:
                if int(ratingsdb[i][0]) < targeted_rating:
                    rating.append(i)
            else:
                if int(ratingsdb[i][1]) < targeted_rating:
                    rating.append(i)  # find corresponding movies with a rating < targeted_rating

    if comparison == '>':
        for i in ratingsdb:
            if is_critic:
                if int(ratingsdb[i][0]) > targeted_rating:
                    rating.append(i)
            else:
                if int(ratingsdb[i][1]) > targeted_rating:
                    rating.append(i)  # find corresponding movies with a rating > targeted_rating

    if comparison == '=':
        for i in ratingsdb:
            if is_critic:
                if int(ratingsdb[i][0]) == targeted_rating:
                    rating.append(i)
            else:
                if int(ratingsdb[i][1]) == targeted_rating:
                    rating.append(i)  # find corresponding movies with a rating = targeted_rating

    return rating


def get_co_actors(actor_name, actor_db):
    """
    This function returns a list of all actors that the given actor has ever worked with in any movie
    """
    if type(actor_name) != str:
        raise TypeError('Actor must be typed as a string!')  # make sure actor is a string
    actor_name = input_trans(actor_name)  # solve case sensitivity and white space
    co_actors = []
    lst = select_where_actor_is(actor_name, actor_db)  # get all the movies the actor acted in
    for i in actor_db:
        for j in actor_db[i]:
            if (j in lst) & (i != actor_name):  # remove the actor himself/herself
                co_actors.append(i)  # add co_actor to the list
    return co_actors


def get_common_movie(actor1, actor2, actor_db):
    """
    This function returns a list of movies where both actors were cast
    """
    if type(actor1) != str or type(actor2) != str:
        raise TypeError('Actor must be typed as a string!')  # make sure actor is a string
    actor1 = input_trans(actor1)
    actor2 = input_trans(actor2)  # solve case sensitivity and white space
    common_mov = []
    if actor1 in actor_db and actor2 in actor_db:  # make sure both actors are in the database
        for i in actor_db[actor1]:
            if i in actor_db[actor2]:
                common_mov.append(i)  # add common movie to the list
    return common_mov

def good_movies(ratingsdb):
    """
    This function returns the set of movies that both critics and the audience have rated above 85
    """
    set1 = set(select_where_rating_is('>', 84, True, ratingsdb))  # good movies for critics
    set2 = set(select_where_rating_is('>', 84, False, ratingsdb))  # good movies for audience
    good_mov = set1.intersection(set2)  # find intersection
    return list(good_mov)


def get_common_actors(movie1, movie2, actor_db):
    """
    # Given a pair of movies, this function returns a list of actors that acted in both movies
    """
    if type(movie1) != str or type(movie2) != str:
        raise TypeError('Movies must be typed as a string!')  # make sure movie is a string
    movie1 = input_trans(movie1)
    movie2 = input_trans(movie2)  # solve case sensitivity and white space
    common_act = []

    set1 = set(select_where_movie_is(movie1, actor_db))  # set of movie1's actor
    set2 = set(select_where_movie_is(movie2, actor_db))  # set of movie2's actor
    common_act = list(set1.intersection(set2))  # find intersection
    return common_act

def input_trans(user_input):
    """
    To transform the user inputs to our standard, solving case sensitivity and leading or trailing whitespace.
    """
    user_input = user_input.title()  # solve case sensitivity
    user_input = user_input.strip()  # solve white space
    y = 'Of'
    if y in user_input:
        user_input = user_input.replace('O', 'o')  # solve the 'of' problem of movie names
    return user_input







def main():
    actor_DB = create_actors_DB('moviedata.txt')
    ratings_DB = create_ratings_DB('movieratings.csv')

    print('*' * 36 + 'Welcome to Movie Trivia!' + '*' * 36)
    print('*' * 5 + 'We have information about actors, the movies they acted in, and ratings of the movies!' + '*' * 5)
    print('')
    playing = True  # using playing to decide whether to continue or not

    while playing:
        print('')
        x = input('''Type 1 to get the list of movies for an actor! 
Type 2 to get the list of actors for a movie!
Type 3 to get the ratings for a movie!
Type 4 to get the movies for a rating condition!
Type 5 to get the actors who have acted with a particular actor!
Type 6 to get the movies critics and the audience have rated highly!
Type 7 to get the actors who have acted in 2 particular movies!
Type 8 to get the movies where 2 particular actors were cast!
Type 9 to insert an actor's information!
Type 10 to insert a movie's information!
Type q to quit.
        ''')
        # get the list of movies for an actor
        if x == '1':
            actor_name = input('Please type the name of the actor:')
            print('list of movies for the actor:', select_where_actor_is(actor_name, actor_DB))
        # get the list of actors for a movie
        if x == '2':
            movie_name = input('Please type the name of the movie:')
            print('list of actors for the movie:', select_where_movie_is(movie_name, actor_DB))

        # get the ratings for a movie
        if x == '3':
            movie_name = input('Please type the name of the movie:')
            movie_name = input_trans(movie_name)  # solve case sensitivity and white space
            if movie_name in ratings_DB:
                print('ratings for the movie:', ratings_DB[movie_name])
            else:
                print('Rating missing. Would you like to update?')

        # get the movies for a rating condition
        if x == '4':
            targeted_rating = int(input('Please type the targeted rating between 0-100:'))
            comparison = input('Please choose "<", ">" or "=":')
            x = input('Please choose critic(Typing "C") or audience(Typing "A") rating:')
            if x == 'C':
                print('list of movies for the rating condition:',
                    select_where_rating_is(comparison, targeted_rating, True, ratings_DB))
            if x == 'A':
                print('list of movies for the rating condition:',
                    select_where_rating_is(comparison, targeted_rating, False, ratings_DB))
            else:
                raise TypeError('Invalid input!')


        # get the actors who have acted with a particular actor
        if x == '5':
            actor_name = input('Please type the name of the actor:')
            print('list of actors who have acted with this actor:', get_co_actors(actor_name, actor_DB))

        # get the movies critics and the audience have rated highly
        if x == '6':
            print('the movies critics and the audience have rated highly:', good_movies(ratings_DB))

        # get the actors who have acted in 2 particular movies
        if x == '7':
            movie1 = input('Please type the name of the first movie:')
            movie2 = input('Please type the name of the second movie:')
            print('the actors for both movies:', get_common_actors(movie1, movie2, actor_DB))

        # get the movies where 2 particular actors were cast
        if x == '8':
            actor1 = input('Please type the name of the first actor:')
            actor2 = input('Please type the name of the second actor:')
            print('the movies for both actors:', get_common_movie(actor1, actor2, actor_DB))

        # insert an actor's information
        if x == '9':
            actor = input('Please type the name of the actor:')
            movies = []
            add = True
            while add:

                movie = input('Please type the name of the movies one by one, and type "X" to quit:')
                movie = input_trans(movie)
                if movie == 'X':
                    add = False
                    break
                else:
                    movies.append(movie)

            insert_actor_info(actor, movies, actor_DB)

        # insert a movie's information
        if x == '10':
            movie = input('Please type the name of the movie:')
            critic = int(input('Please type the ratings of critic :'))
            audience = int(input('Please type the ratings of audience:'))
            ratings = [critic, audience]
            insert_rating(movie, ratings, ratings_DB)

        # type q to quit
        if x == 'q':
            playing = False


    
if __name__ == '__main__':
    main()
