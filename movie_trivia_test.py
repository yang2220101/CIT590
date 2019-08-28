import unittest

from movie_trivia import *

class Movie_Trivia_Test(unittest.TestCase):

    def setUp(self):
        '''Runs before each and every test method.
        Creates dictionary databases to be used by each test method.'''

        #create class-wide accessible actors_DB
        self.actor_DB = create_actors_DB('moviedata.txt')

        #create class-wide accessible ratings_DB
        self.ratings_DB = create_ratings_DB('movieratings.csv')


    def tearDown(self):
        pass


    def test_create_actors_DB(self):

        # Use "self" when referencing the class-wide variable actor_DB
        # Gets and tests the movies for Meryl Streep
        self.assertListEqual(self.actor_DB['Meryl Streep'], ["Doubt", "Sophie's Choice", "The Post",
                                                             'Mary Poppins Returns', 'Everything Is Copy'])

        #Insert other test cases here
        # Gets and tests the movies for Tom Hanks
        self.assertListEqual(self.actor_DB['Tom Hanks'], ["The Post", "Catch Me If You Can", "Cast Away",
                                                          'Cloud Atlas', 'Sully', 'Everything Is Copy',
                                                          'Bridge of Spies'])

    def test_create_ratings_DB(self):

        # Use "self" when referencing the class-wide variable ratings_DB
        # Gets and tests the ratings for Arrival
        self.assertListEqual(self.ratings_DB['Arrival'], ['94', '82'])

        #Insert other test cases here
        # Gets and tests the ratings for Jaws
        self.assertListEqual(self.ratings_DB['Cloud Atlas'], ['66', '66'])


    #Insert test methods for other funmctions here

    def test_insert_actor_info(self):
        actordb = {'Aa Aa': [1, 2, 3], 'Bb Bb': [4, 5, 6]}
        # to test if the input 'movies' is a list
        self.assertRaises(TypeError, insert_actor_info, 'Aa Aa', 1, actordb)
        # to test if we can add a new actor with new movies
        insert_actor_info('Cc Cc', [7, 8, 9], actordb)
        self.assertEqual(actordb['Cc Cc'], [7, 8, 9])
        # to test if we can add movies to existent actor
        insert_actor_info('Aa Aa', [10, 11, 12], actordb)
        self.assertCountEqual(actordb['Aa Aa'], [1, 2, 3, 10, 11, 12])
        # to test if it is not case sensitive
        insert_actor_info('BB bB', [13, 14, 15], actordb)
        self.assertCountEqual(actordb['Bb Bb'], [4, 5, 6, 13, 14, 15])
        # to test if leading or trailing whitespace matters
        insert_actor_info(' Cc Cc ', [16, 17, 18], actordb)
        self.assertCountEqual(actordb['Cc Cc'], [7, 8, 9, 16, 17, 18])

    def test_insert_rating(self):
        ratingsdb = {'A': [50, 90], 'B': [95, 20]}
        #to test if the 'ratings' is a list
        self.assertRaises(TypeError, insert_rating, 'A', 1, ratingsdb)
        # to test if we can add a new movie with new ratings
        insert_rating('C', [80, 80], ratingsdb)
        self.assertEqual(ratingsdb['C'], [80, 80])
        # to test if we can update ratings to existent movie
        insert_rating('A', [60, 100], ratingsdb)
        self.assertEqual(ratingsdb['A'], [60, 100])
        # to test if it is not case sensitive
        insert_rating('b', [90, 30], ratingsdb)
        self.assertEqual(ratingsdb['B'], [90, 30])
        # to test if leading or trailing whitespace matters
        insert_rating(' C ', [75, 85], ratingsdb)
        self.assertEqual(ratingsdb['C'], [75, 85])

    def test_select_where_actor_is(self):
        actordb = {'Aa Aa': ['A', 'B', 'C'], 'Bb Bb': ['D', 'E', 'F'], 'Cc Cc': ['G', 'H', 'I']}
        # to test if given an existent actor, this function returns a corresponding list
        self.assertCountEqual(['A', 'B', 'C'], select_where_actor_is('Aa Aa', actordb))
        # to test if given a non-existent actor, this function returns an empty list
        self.assertEqual([], select_where_actor_is('Dd Dd', actordb))
        # to test if it is not case sensitive
        self.assertEqual(select_where_actor_is('BB bB', actordb), ['D', 'E', 'F'])
        # to test if leading or trailing whitespace matters
        self.assertEqual(select_where_actor_is(' Cc Cc ', actordb), ['G', 'H', 'I'])

    def test_select_where_movie_is(self):
        actordb = {'1': ['A', 'B', 'C'], '2': ['B', 'C', 'D'], '3': ['A', 'D', 'F']}
        #  to test if given an existent movie, this function returns a corresponding list
        self.assertCountEqual(['1', '2'], select_where_movie_is('C', actordb))
        #  to test if given a non-existent movie, this function returns an empty list
        self.assertEqual([], select_where_movie_is('4', actordb))
        # to test if it is not case sensitive
        self.assertCountEqual(select_where_movie_is('b', actordb), ['1', '2'])
        # to test if leading or trailing whitespace matters
        self.assertCountEqual(select_where_movie_is(' D ', actordb), ['2', '3'])

    def test_select_where_rating_is(self):
        ratingsdb = {'A': [50, 90], 'B': [25, 20], 'C': [25, 65], 'D': [40, 65]}
        # to test if comparison is a string
        self.assertRaises(TypeError, select_where_rating_is, ['<'], 50, True, ratingsdb)
        # to test if comparison is existent
        self.assertRaises(TypeError, select_where_rating_is, '==', 50, True, ratingsdb)
        # to test if is_critic is a boolean
        self.assertRaises(TypeError, select_where_rating_is, '<', 50, 1, ratingsdb)
        # to test if targeted_rating is an integer
        self.assertRaises(TypeError, select_where_rating_is, '<', 5.6, True, ratingsdb)
        # to test if input is out of range, this function should return an empty list
        self.assertEqual([], select_where_rating_is('<', 0, True, ratingsdb))
        # to test if targeted_rating isn’t a number
        self.assertRaises(TypeError, select_where_rating_is, '<', '50', True, ratingsdb)
        # to test if comparisons with leading or trailing whitespace
        self.assertRaises(TypeError, select_where_rating_is, [' < '], 50, True, ratingsdb)
        # to test some examples of expected output
        self.assertCountEqual(['A', 'B', 'C', 'D'], select_where_rating_is('>', 0, True, ratingsdb))
        self.assertCountEqual(['C', 'D'], select_where_rating_is('=', 65, False, ratingsdb))
        self.assertCountEqual(['B', 'C'], select_where_rating_is('<', 30, True, ratingsdb))

    def test_get_co_actors(self):
        actor_db = {'Aa Aa': ['A', 'B'], 'Bb Bb': ['B', 'C', 'D'], 'Cc Cc': ['D', 'E', 'F'], 'Dd Dd': ['A', 'C']}
        # to test if actor_name is a string
        self.assertRaises(TypeError, get_co_actors, 1, actor_db)
        # to test if it is not case sensitive
        self.assertCountEqual(get_co_actors('AA aa', actor_db), ['Bb Bb', 'Dd Dd'])
        # to test if leading or trailing whitespace matters
        self.assertCountEqual(get_co_actors(' Cc Cc ', actor_db), ['Bb Bb'])
        # to test some examples of expected output
        self.assertCountEqual(get_co_actors('Bb Bb', actor_db), ['Cc Cc', 'Aa Aa', 'Dd Dd'])

    def test_get_common_movie(self):
        actor_db = {'Aa Aa': ['A', 'B'], 'Bb Bb': ['B', 'C', 'D'], 'Cc Cc': ['D', 'E', 'F'], 'Dd Dd': ['A', 'C']}
        # to test if actor_name is a string
        self.assertRaises(TypeError, get_common_movie, 1, 2, actor_db)
        # if the two actors have never worked together, or there is a non-existent actors, it returns an empty ist
        self.assertEqual([], get_common_movie('Aa Aa', 'Cc Cc', actor_db))
        self.assertEqual([], get_common_movie('Aa Aa', 'Ee Ee', actor_db))
        # to test if it is not case sensitive
        self.assertCountEqual(get_common_movie('AA aa', 'bB bB', actor_db), ['B'])
        # to test if leading or trailing whitespace matters
        self.assertCountEqual(get_common_movie(' Cc Cc ', 'Bb Bb', actor_db), ['D'])

    def test_good_movies(self):
        ratingsdb = {'A': [85, 90], 'B': [25, 20], 'C': [85, 65], 'D': [90, 95]}
        # to test if it works
        self.assertCountEqual(good_movies(ratingsdb), ['A', 'D'])

    def test_get_common_actors(self):
        actor_db = {'Aa Aa': ['A', 'B'], 'Bb Bb': ['B', 'C', 'D'], 'Cc Cc': ['D', 'E', 'F'], 'Dd Dd': ['A', 'C']}
        # to test if actor_name is a string
        self.assertRaises(TypeError, get_common_actors, 1, 2, actor_db)
        # if the movies have no actors in common, or there is a non-existent movie, it returns an empty list
        self.assertEqual([], get_common_actors('A', 'F', actor_db))
        self.assertEqual([], get_common_actors('A', 'G', actor_db))
        # to test if it is not case sensitive
        self.assertCountEqual(get_common_actors('a', 'b', actor_db), ['Aa Aa'])
        # to test if leading or trailing whitespace matters
        self.assertCountEqual(get_common_actors(' C ', ' A', actor_db), ['Dd Dd'])

    def test_input_trans(self):
        # to test if works
        self.assertEqual('Aa Bb Cc', input_trans(' aA bb CC '))
        self.assertEqual('Man of Steel', input_trans('mAn OF steeL'))














unittest.main()
