import {createApi} from '@reduxjs/toolkit/query/react';
import {fetchBaseQuery} from '@reduxjs/toolkit/dist/query/react';

/** Api slice for querying survey results data. */
export const resultApi = createApi({
    reducerPath: 'results',
    baseQuery: fetchBaseQuery({
        baseUrl: 'http://локальный хост: 3030/результаты»,
        prepareHeaders: (headers, {getState}) => {
            const token = getState().auth.token;

            if (token) {
                headers.set('Authorization', `Bearer ${token}`);
            }

            return headers;
        }
    }),
    endpoints: (builder) => ({
        // Возвращает все результаты данного опроса.
        getResultsForSurvey: builder.query({
            query: surveyId => `?surveyId=${surveyId}`,
            transformResponse: (response) => response.data
        }),

        // Добавляет результаты к данному опросу.
        createResult: builder.mutation({
            query: ({surveyId, answer}) => ({method: 'POST', body: {surveyId, content: answer}}),
        })
    }),
});

export const {useGetResultsForSurveyQuery, useCreateResultMutation} = resultApi;