package com.example.wagba_android_application.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wagba_android_application.model.Dish;
import com.example.wagba_android_application.model.Restaurant;

@Database(entities = {Restaurant.class, Dish.class}, version = 1, exportSchema = false)
public abstract class myRoomDatabase extends androidx.room.RoomDatabase {

    public abstract RestaurantDao restaurantDao();

    public abstract DishDao dishDao();

    private static myRoomDatabase INSTANCE;

    public static myRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (myRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    myRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static Callback sRoomDatabaseCallback = new Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateRestaurantsDbAsync(INSTANCE).execute();
            new PopulateDishesDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateRestaurantsDbAsync extends AsyncTask<Void, Void, Void> {

        private final RestaurantDao mDao;
        String[] restaurantNames = {"Papa John's Pizza", "Hamza",
                "McDonald's", "Crepe Door", "ElDahan", "PEKING",
                "TGI Friday's", "Spectra", "Awlad Ta2ta2", "Dunkin'"};
        String[] restaurantDescriptions = {"Fast Food - Italian - Pizza", "Egyptian - Fast Food - Grill - Oriental",
                "Burger - Desserts - Fast Food - Sandwiches", "Crepe - Fast Food", "Egyptian - Grill - Oriental - Sandwiches",
                "Chinese - Grill - Vegetarian", "American - Burger - Steaks - Tex-Mex",
                "American  Coffee - Drinks  Grill  International",
                "Sandwiches - Seafood", "Coffee - Drinks - Desserts"};
        String[] restaurantImages = {"papajohns", "hamza",
                "mcdonalds", "crepedoor", "eldahan", "peking",
                "tgifriday", "spectra", "awladta2ta2", "dunkin"};


        PopulateRestaurantsDbAsync(myRoomDatabase db) {
            mDao = db.restaurantDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            for (int i = 0; i < restaurantNames.length; i++) {
                Restaurant res = new Restaurant(restaurantNames[i], restaurantDescriptions[i], restaurantImages[i]);
                mDao.insert(res);
            }
            return null;
        }
    }


    private static class PopulateDishesDbAsync extends AsyncTask<Void, Void, Void> {

        private final DishDao mDao;
        String[] restaurantNames = {"Papa John's Pizza", "Hamza",
                "McDonald's", "Crepe Door", "ElDahan", "PEKING",
                "TGI Friday's", "Spectra", "Awlad Ta2ta2", "Dunkin'"};

        String[][] dishNames = {
                {"Chicken Ranch", "Pepperoni", "Chicken BBQ", "Smoky Cheese"},
                {"Chicken Shawerma Fattah", "Chicken Shawerma Sandwich", "Mix Shawerma Fattah", "Meat Shawerma Fattah"},
                {"Big Mac", "Big Tasty", "Chicken Mac", "Double Big Tasty"},
                {"Super Cordon Bleu Crepe", "Crispy Chicken Crepe", "Zinger Crepe", "Chicken Friday Crepe"},
                {"1/2 Boneless Grilled Chicken", "1/2 Grilled Chicken", "1/2 Grilled Chicken Fillet", "1/2 Grilled Chicken Meal"},
                {"Crisp Fried Potato", "Crisp Fried Calamari", "Trio Mix", "Crisp Shandong Vegetables"},
                {"Cheese Nachos", "Cowboy Burger", "Yucatan Chicken Salad", "Buffalo Wings"},
                {"Hickory Smoked Chicken", "Grilled Chicken Fettuccine Pasta", "Fried Mozzarella", "Grilled Chicken Blue Ripple"},
                {"Seafood Pasta", "Turbo Seafood with Cream and Mozzarella Tagen", "Boneless Seafood Soup", "Jumbo Grilled Shrimp"},
                {"Choco Biscuit Donut", "Filled Ring Bavarian Donut", "Mocha Cream Filling Donut", "Strawberry Frosted Sprinkles Donut"},
        };

        String[][] dishDescription = {
                {"Grilled chicken, tomato, fresh mushroom with ranch sauce", "Loaded with pepperoni and extra mozzarella cheese", "Grilled chicken, onion, fresh mushroom and BBQ sauce drizzled on top", "Smoked veal, smoked cheese, cheddar, gouda, onion with creamy sauce"},
                {"Chicken shawerma slices, yellow basmati rice, toasted bread, garlic dip", "Garlic dip, pickled cucumber", "Chicken shawerma slices, meat shawerma slices, yellow basmati rice, toasted bread, garlic dip", "Meat shawerma slices, yellow basmati rice, toasted bread, garlic dip"},
                {"Two beef patties, melting signature cheese, crisp shredded lettuce, onions, pickles and a bun in the middle all between a toasted sesame seed bun", "Juicy beef patty smothered in three extraordinary slices of Emmental cheese and topped with sliced tomato, shredded lettuce, onions and that special Big Tasty sauce.", "A delicious combination of breaded chicken patties, crisp lettuce, melting cheese, onions, pickles, and our special sauce, all framed between a toasted sesame seed bun", "Two juicy beef patties smothered between slices of Emmental cheese, topped with sliced tomato, shredded lettuce, onions and that special Big Tasty sauce."},
                {"Fried chicken strips, smoked beef, mozzarella", "Fried chicken breasts, smoked turkey, mozzarella", "Spicy strips and mozzarella cheese", "Grilled chicken thighs, mushroom, pepper, olives and mozzarella cheese"},
                {"Served with salad and bread", "Served with salad and bread", "Served with salad and bread", "Served with khalta rice, salad and bread"},
                {"Served with your choice of plum sauce or spicy kun pao sauce", "Served with your choice of plum sauce or spicy kun pao sauce", "Peking rolls (2 pieces from one type), fried won ton (beef or chicken 6 pieces) and crispy mushroom", "With chili"},
                {"Crisp corn tortilla chips topped with melted colby cheese, jalapeno slices served with the classic trio: pico de gallo, guacamole, and sour cream", "Australian beef burger piled high with slow-cooked BBQ beef, served with melted cheddar cheese, roasted beef bacon, frizzled onions and horseradish sauce on a brioche bun", "Pulled chicken breast served on a bed of mixed greens, romaine lettuce, avocado, mix cheese, diced tomato, red onion, cilantro, crisp tostadas, drizzled with cilantro", "Fried chicken wings tossed in spicy buffalo sauce served with crisp celery sticks and blue cheese dip"},
                {"Served with 2 side dishes or 1 pasta", "White, red, basil or olive oil basil sauce", "Served with your choice of sauce", "Served with 2 side dishes or 1 penne pasta"},
                {"Penne or spaghetti, with cream or salsa", "Shrimp, squid and fillet", "Squid, shrimp and fillet", "Grilled with khalata or butterfly"},
                {"New Choco-Biscuit Donut with Chocolate and Biscuits on topings filled with biscuit cream", "this classic donut is a yeast shell, with Bavarian Kreme filling, topped with chocolate icing", "Mocha Cream Donut filled with a marvelous tasty mocha cream", "Strawberry frosted topped with rainbow sprinkle."},
        };

        String[][] dishPrice = {
                {"255", "245", "245", "275"},
                {"71", "66", "81", "275"},
                {"88", "132", "120", "149"},
                {"60", "55", "50", "55"},
                {"78", "75", "68", "109"},
                {"34", "88", "69", "39"},
                {"128", "177", "149", "129"},
                {"200", "134", "89", "235"},
                {"110", "140", "120", "400"},
                {"18.99", "18.99", "18.99", "18.99"},
        };
        String[][] dishImage = {
                {"chicken_ranch", "pepperoni", "chicken_bbq", "smoky_cheese"},
                {"chicken_shawerma_fattah", "chicken_shawerma_sandwich", "mix_shawerma_fattah", "meat_shawerma_fattah"},
                {"big_mac", "big_tasty", "chicken_mac", "double_big_tasty"},
                {"super_cordon_bleu_crepe", "crispy_chicken_crepe", "chicken_mac", "chicken_friday_crepe"},
                {"half_boneless_grilled_chicken", "half_grilled_chicken", "half_grilled_chicken_fillet", "half_grilled_chicken_meal"},
                {"crisp_fried_potato", "crisp_fried_calamari", "trio_mix", "crisp_shandong_vegetables"},
                {"cheese_nachos", "cowboy_burger", "yucatan_chicken_salad", "buffalo_wings"},
                {"hickory_smoked_chicken", "grilled_chicken_fettuccine_pasta", "fried_mozzarella", "grilled_chicken_blue_ripple"},
                {"seafood_pasta", "turbo_seafood_with_cream_and_mozzarella_tagen", "boneless_seafood_soup", "large_grilled_shrimp"},
                {"choco_biscuit_donut", "filled_ring_bavarian_donut", "mocha_cream_filling", "strawberry_frosted_sprinkles"},
        };


        PopulateDishesDbAsync(myRoomDatabase db) {
            mDao = db.dishDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            for (int i = 0; i < restaurantNames.length; i++) {
                for (int j = 0; j < dishDescription[i].length; j++) {
                    Dish dish = new Dish(dishNames[i][j], restaurantNames[i], dishDescription[i][j], dishPrice[i][j], dishImage[i][j]);
                    mDao.insert(dish);
                }
            }
            return null;
        }
    }
}
