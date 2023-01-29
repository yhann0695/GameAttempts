class ChallengesApiClient {
    static SERVER_URL = 'http://localhost:8000';
    static GET_CHALLENGE = '/challenges/random';
    static POST_RESULT = '/attempts';
    static GET_USERS_BY_IDS = '/users';
    static GET_ATTEMPTS_BY_ALIAS = '/attempts?alias=';

    static challenge() {
        return fetch(ChallengesApiClient.SERVER_URL + ChallengesApiClient.GET_CHALLENGE);
    }

    static sendGuess(user, a, b, guess) {
        return fetch(ChallengesApiClient.SERVER_URL + ChallengesApiClient.POST_RESULT, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(
                {
                    userAlias: user,
                    factorA: a,
                    factorB: b,
                    guess: guess
                }
            )
        });
    }

    static getAttempts(userAlias) {
        return fetch(
            ChallengesApiClient.SERVER_URL + 
            ChallengesApiClient.GET_ATTEMPTS_BY_ALIAS + userAlias
        );
    }

    static getUser(userIds) {
        return fetch(
            ChallengesApiClient.SERVER_URL + 
            ChallengesApiClient.GET_USERS_BY_IDS + 
            '/' + userIds.join(',')
        );
    }
}

export default ChallengesApiClient;