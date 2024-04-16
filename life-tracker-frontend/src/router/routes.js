
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    meta: {
      requiresAuth: true
    },
    children: [
      // { path: '', component: () => import('pages/IndexPage.vue') },
      // { path: '/test', component: () => import('pages/TestTree.vue')},
      // { path: '/official-tree', component: () => import('pages/OfficialTree.vue')},
      { path: '', component: () => import('pages/WorkSkills.vue')}
    ]
  },
  {
    path: '/auth',
    children: [
      {
        path: 'sign-in',
        name: 'SignIn',
        component: () => import('pages/Authentication/SignIn.vue')
      },
      {
        path: 'sign-up',
        name: 'SignUp',
        component: () => import('pages/Authentication/SignUp.vue')
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]
export default routes
