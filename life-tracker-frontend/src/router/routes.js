
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/IndexPage.vue') },
      { path: '/test', component: () => import('pages/TestTree.vue')},
      { path: '/official-tree', component: () => import('pages/OfficialTree.vue')},
      { path: '/pack-from-inet', component: () => import('pages/WorkSkills.vue')}
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('pages/LoginPage.vue')
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue')
  }
]

export default routes
